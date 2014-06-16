//   Copyright 2014 Commonwealth Bank of Australia
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.

package au.com.cba.omnia.humbug

import com.twitter.scrooge.frontend._
import com.twitter.scrooge.ast._

import scalaz._, Scalaz._

object Generator {
  def generateDoc(doc: Document): Seq[(String, String)] = {
    val pck = doc.namespace("scala").cata(
      x => s"package ${x.toLowerCase.fullName}\n",
      ""
    )

    doc.structs.collect { case s: Struct => generateStruct(s, doc.namespace("scala")) }
  }

  def generateStruct(struct: Struct, namespace: Option[Identifier]): (String, String) = {
    val className = struct.sid.toTitleCase.fullName
    val fields    = struct.fields

    val pck = namespace.cata(
      x => s"package ${x.toLowerCase.fullName}",
      ""
    )
    val path = namespace.cata(_.toLowerCase.fullName.replaceAll("\\.", "/") + s"/$className.scala", s"$className.scala")
 
    path -> s"""$pck

import org.apache.thrift.protocol.{TProtocol, TProtocolException, TStruct, TField, TType}

import com.twitter.scrooge.{ThriftStruct, ThriftStructCodec3}

import au.com.cba.omnia.humbug.HumbugThriftStruct

class $className  extends ThriftStruct with java.io.Serializable with HumbugThriftStruct {
  import $className._

  ${fields.map(generateFieldMembers).mkString("\n  ")}

  override def write(_oprot: TProtocol): Unit = {
    _oprot.writeStructBegin(new TStruct("$className"))
${fields.map(generateFieldWriteAccess).mkString("\n")}
    _oprot.writeFieldStop()
    _oprot.writeStructEnd()
  }

  override def equals(other: Any) = other match {
    case null => false
    case that: $className =>
      List(${fields.map(f => "this." + varName(f)).mkString(", ")}) == List(${fields.map(f => "that." + varName(f)).mkString(", ")})
    case _ => false
  }

  override def toString: String = "$className(" + ${fields.map(generateToString)}.mkString(", ") + ")"
}

object $className extends ThriftStructCodec3[$className] {
  val struct = new TStruct("$className")
  ${fields.map(generateFieldVals).mkString("\n  ")}

  override def encode(_item: $className, _oproto: TProtocol): Unit =
    _item.write(_oproto)

  override def decode(_iprot: TProtocol): $className = {
    val item  = new $className()
    var _done = false

    _iprot.readStructBegin()
    while (!_done) {
      val _field = _iprot.readFieldBegin()
      if (_field.`type` == TType.STOP) {
        _done = true
      } else {
        _field.id match {
${fields.map(generateFieldReadAccess).mkString("\n")}
        }
        _iprot.readFieldEnd()
      }
    }   
    _iprot.readStructEnd()

    item
  }

${fields.map(f => generateFieldWriteCode(f) + "\n" + generateFieldReadCode(f)).mkString("\n\n")}

  def ttypeToHuman(byte: Byte) = {
    // from https://github.com/apache/thrift/blob/master/lib/java/src/org/apache/thrift/protocol/TType.java
    byte match {
      case TType.STOP   => "STOP"
      case TType.VOID   => "VOID"
      case TType.BOOL   => "BOOL"
      case TType.BYTE   => "BYTE"
      case TType.DOUBLE => "DOUBLE"
      case TType.I16    => "I16"
      case TType.I32    => "I32"
      case TType.I64    => "I64"
      case TType.STRING => "STRING"
      case TType.STRUCT => "STRUCT"
      case TType.MAP    => "MAP"
      case TType.SET    => "SET"
      case TType.LIST   => "LIST"
      case TType.ENUM   => "ENUM"
      case _            => "UNKNOWN"
    }
  }
}
"""
  }

  def generateFieldMembers(field: Field) = {
    val name = varName(field)
    val typ  = scalaType(field)
    s"""var $name: $typ = ${defaultValue(field)}
  def _${field.index}: $typ = $name
  def _${field.index}_=(x: $typ): Unit = $name = x
"""
  }

  def generateFieldWriteAccess(field: Field) = field.fieldType match {
    case TByte | TI16 | TI32 | TI64 | TDouble | TBool =>
      s"""    ${writeFieldName(field)}(${varName(field)}, _oprot)"""
    case _ =>
      s"""    if (${varName(field)} ne null) ${writeFieldName(field)}(${varName(field)}, _oprot)"""
  }

  def generateFieldReadAccess(field: Field) = {
    val idx = field.index
    s"          case $idx => item._${idx} = ${readFieldName(field)}(_field.`type`, _iprot)"
  }

  def generateFieldWriteCode(field: Field) = {
    s"""  private def ${writeFieldName(field)}(item: ${scalaType(field)}, _oprot: TProtocol): Unit = {
    _oprot.writeFieldBegin(new TField("${field.originalName}", TType.${constType(field)}, ${field.index}))
    _oprot.${writeThriftName(field)}(item)
    _oprot.writeFieldEnd()
  }
  """
  }

  def generateFieldReadCode(field: Field) = {
    val ttype = "TType." + constType(field)
    
    s"""  private def ${readFieldName(field)}(_typ: Byte, _iprot: TProtocol): ${scalaType(field)} = _typ match {
    case $ttype => _iprot.${readThriftName(field)}
    case _actualType => throw new TProtocolException(
      "Received wrong type for field ${field.originalName} (expected=$ttype, actual=%s).".format(
        ttypeToHuman(_actualType)
      )
    )}"""
  }

  def generateEquals(field: Field) = s"this.${varName(field)} == that.${varName(field)}"
  def generateToString(field: Field) = s"this.${varName(field)}"

  def generateFieldVals(field: Field) = {
    val ttype    = "TType." + constType(field)
    val origName = field.originalName
    val name = field.sid.toTitleCase.fullName +  "Field"
    val manifestName = name +  "Manifest"
    val typ  = scalaType(field)

    s"""val $name = new TField("$origName", $ttype, ${field.index})
  val $manifestName = implicitly[Manifest[$typ]]"""
  }

  def varName(field: Field) = field.sid.toCamelCase.fullName
  def writeFieldName(field: Field) = "write" + field.sid.toTitleCase.fullName
  def readFieldName(field: Field) = "read" + field.sid.toTitleCase.fullName

  def scalaType(field: Field) = field.fieldType match {
    case TBool   => "Boolean"
    case TByte   => "Byte"
    case TI16    => "Short"
    case TI32    => "Int"
    case TI64    => "Long"
    case TDouble => "Double"
    case TString => "String"
    case t       => throw new Exception(s"Unsupported field type: $t")
  }

  def constType(field: Field) = field.fieldType match {
    case TBool   => "BOOL"
    case TByte   => "BYTE"
    case TI16    => "I16"
    case TI32    => "I32"
    case TI64    => "I64"
    case TDouble => "DOUBLE"
    case TString => "STRING"
    case t       => throw new Exception(s"Unsupported field type: $t")
  }

  def defaultValue(field: Field) = field.fieldType match {
    case TBool => "false"
    case TByte | TI16 | TI32 | TI64 => "0"
    case TDouble => "0.0"
    case _ => "null"
  }

  def readThriftName(field: Field)  = "read" + field.fieldType.toString.tail
  def writeThriftName(field: Field) = "write" + field.fieldType.toString.tail
}
