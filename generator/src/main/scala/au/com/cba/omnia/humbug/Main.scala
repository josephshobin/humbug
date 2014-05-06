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
