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

import java.io.{File, FileWriter}

import com.twitter.scrooge.frontend._
import com.twitter.scrooge.ast._

object Main {
  def fromFile(path: String): Seq[(String, String)] = {
    val importer = Importer(new File("."))
    val parser = new ThriftParser(importer, true, false)

    val doc = parser.parseFile(path)
    Generator.generateDoc(doc)
  }

  def generate(dst: String, thriftFiles: List[String]): Unit = {
    val base = new File(dst)

    thriftFiles.foreach { f =>
      fromFile(f).foreach { case (sub, code) =>
        val dst = new File(base, sub)
        val dir = dst.getParentFile
        if (dir != null && !dir.exists()) {
          dir.mkdirs()
        }

        val writer = new FileWriter(dst)
        writer.write(code)
        writer.close()
      }
    }
  }

  def main(args: Array[String]): Unit = {
    if (args.length < 2) {
      println("Main dst_path thrift_file1 thrift_file2 ...")
      sys.exit(1)
    }

    generate(args(0), args.toList.tail)
  }
}
