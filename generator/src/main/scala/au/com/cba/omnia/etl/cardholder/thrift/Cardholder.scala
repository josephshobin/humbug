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

package au.com.cba.omnia.etl.cardholder.thrift

import org.apache.thrift.protocol.{TProtocol, TProtocolException, TStruct, TField, TType}

import com.twitter.scrooge.{ThriftStruct, ThriftStructCodec3}

class Cardholder  extends ThriftStruct with java.io.Serializable {
  import Cardholder._

  var bcmCorporationNumber: String = null
  def _bcmCorporationNumber_=(x: String): Unit = bcmCorporationNumber = x
  def _1: String = bcmCorporationNumber
  def _1_=(x: String): Unit = bcmCorporationNumber = x

  var bcmAccountNo: String = null
  def _bcmAccountNo_=(x: String): Unit = bcmAccountNo = x
  def _2: String = bcmAccountNo
  def _2_=(x: String): Unit = bcmAccountNo = x


  override def write(_oprot: TProtocol): Unit = {
    _oprot.writeStructBegin(new TStruct("Cardholder"))
    if (bcmCorporationNumber ne null) writeBcmCorporationNumber(bcmCorporationNumber, _oprot)
    if (bcmAccountNo ne null) writeBcmAccountNo(bcmAccountNo, _oprot)
    _oprot.writeFieldStop()
    _oprot.writeStructEnd()
  }
}

object Cardholder extends ThriftStructCodec3[Cardholder] {
  override def encode(_item: Cardholder, _oproto: TProtocol): Unit =
    _item.write(_oproto)

  override def decode(_iprot: TProtocol): Cardholder = {
    val item  = new Cardholder()
    var _done = false

    _iprot.readStructBegin()
    while (!_done) {
      val _field = _iprot.readFieldBegin()
      if (_field.`type` == TType.STOP) {
        _done = true
      } else {
        _field.id match {
          case 1 => item._1 = readBcmCorporationNumber(_field.`type`, _iprot)
          case 2 => item._2 = readBcmAccountNo(_field.`type`, _iprot)
        }
        _iprot.readFieldEnd()
      }
    }
    _iprot.readStructEnd()

    item
  }

  private def writeBcmCorporationNumber(item: String, _oprot: TProtocol): Unit = {
    _oprot.writeFieldBegin(new TField("bcm_corporation_number", TType.STRING, 1))
    _oprot.writeString(item)
    _oprot.writeFieldEnd()
  }


  private def readBcmCorporationNumber(_typ: Byte, _iprot: TProtocol): String = _typ match {
    case TType.STRING => _iprot.readString
    case _actualType => throw new TProtocolException(
      "Received wrong type for field bcm_corporation_number (expected=TType.STRING, actual=%s).".format(
        ttypeToHuman(_actualType)
      )
    )}
  private def writeBcmAccountNo(item: String, _oprot: TProtocol): Unit = {
    _oprot.writeFieldBegin(new TField("bcm_account_no", TType.STRING, 2))
    _oprot.writeString(item)
    _oprot.writeFieldEnd()
  }


  private def readBcmAccountNo(_typ: Byte, _iprot: TProtocol): String = _typ match {
    case TType.STRING => _iprot.readString
    case _actualType => throw new TProtocolException(
      "Received wrong type for field bcm_account_no (expected=TType.STRING, actual=%s).".format(
        ttypeToHuman(_actualType)
      )
    )}

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
