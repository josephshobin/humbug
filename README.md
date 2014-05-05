# Humbug

[![Build Status](https://magnum.travis-ci.com/CommBank/humbug.svg?token=A3xq7fpHLyey1yCrNASy&branch=master)](https://magnum.travis-ci.com/CommBank/humbug)
[![Gitter chat](https://badges.gitter.im/CommBank/humbug.png)](https://gitter.im/CommBank/humbug)

Humbug is a very simple scrooge replacement for Thrift structs with more than 254 fields. It only supports very simple Thrift structs with primitive types.

Due to the JVM limit of only having methods with less than 255 parameters the Scala code generated to represent the Thrift struct is mutable and is initialised by setting each member individually.
