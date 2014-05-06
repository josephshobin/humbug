package au.com.cba.omnia.humbug.test

import scalaz._, Scalaz._

import scalaz.scalacheck.ScalazArbitrary._
import scalaz.scalacheck.ScalaCheckBinding._

import org.scalacheck._, Arbitrary._

object Arbitraries {
  implicit def TypesArbitrary: Arbitrary[Types] = Arbitrary((
    arbitrary[String]  |@|
    arbitrary[Boolean] |@|
    arbitrary[Short]   |@|
    arbitrary[Int]     |@|
    arbitrary[Long]    |@|
    arbitrary[Double]  |@|
    arbitrary[Byte]
  ){ case (string, boolean, short, int, long, double, byte) =>
      val types = new Types()
      types.stringField = string
      types.booleanField = boolean
      types.shortField = short
      types.intField = int
      types.longField = long
      types.doubleField = double
      types.byteField = byte

      types
  })
}
