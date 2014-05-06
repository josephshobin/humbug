version := "0.1"

scalaVersion := "2.10.2"

au.com.cba.omnia.humbug.HumbugSBT.humbugSettings

libraryDependencies ++= depend.scrooge() ++ Seq(
  "au.com.cba.omnia" %% "humbug-core" % System.getProperty("plugin.version")
)
