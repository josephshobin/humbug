# Humbug

[![Build Status](https://travis-ci.org/CommBank/humbug.svg?branch=master)](https://travis-ci.org/CommBank/humbug)
[![Gitter chat](https://badges.gitter.im/CommBank/humbug.png)](https://gitter.im/CommBank/humbug)

Humbug is a very simple scrooge replacement for Thrift structs with more than 254 fields. It only supports very simple Thrift structs with primitive types.

Due to the JVM limit of only having methods with less than 255 parameters the Scala code generated to represent the Thrift struct is mutable and is initialised by setting each member individually. Classes generated by Humbug are tagged with the trait `HumbugThriftStruct`.

To use the Humbug sbt pluging add 
```
resolvers ++= Seq(
  Resolver.url("commbank-releases-ivy", new URL("http://commbank.artifactoryonline.com/commbank/ext-releases-local-ivy"))(Patterns("[organization]/[module]_[scalaVersion]_[sbtVersion]/[revision]/[artifact](-[classifier])-[revision].[ext]")),
  "commbank-releases" at "http://commbank.artifactoryonline.com/commbank/ext-releases-local"
)

addSbtPlugin("au.com.cba.omnia" % "humbug-plugin" % pluginVersion)

```
to `project/plugins.sbt` and 

```
au.com.cba.omnia.humbug.HumbugSBT.humbugSettings

libraryDependencies ++= depend.scrooge() ++ Seq(
  "au.com.cba.omnia" %% "humbug-core" % version
)
```
to `build.sbt`
