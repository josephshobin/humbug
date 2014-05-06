package au.com.cba.omnia.humbug

import com.twitter.scrooge.ThriftStruct

/**
  * HumbugThriftStruct is a marker trait to indicate that a thrift struct was generated using
  * the Humbug generator. This means:
  *  * it has an empty constructor to avoid the JVM 254 method parameters limit.
  *  * that the fields of the thrift struct are mutable and are used to construct it.
  *  * that it has members named after the fields in the thrift file with getters and setters.
  *  * that is has getters and setters `_1` corresponding to the index of a field.
  */
trait HumbugThriftStruct { self: ThriftStruct =>
}
