import sbt._
import Keys._

import au.com.cba.omnia.uniform.core.standard.StandardProjectPlugin._
import au.com.cba.omnia.uniform.core.version.UniqueVersionPlugin._
import au.com.cba.omnia.uniform.dependency.UniformDependencyPlugin._
import au.com.cba.omnia.uniform.assembly.UniformAssemblyPlugin._

import sbtassembly.Plugin._, AssemblyKeys._

object build extends Build {
  type Sett = Project.Setting[_]

  val compileThrift = TaskKey[Seq[File]](
    "compile-thrift", "generate thrift needed for tests")

  lazy val standardSettings: Seq[Sett] =
    Defaults.defaultSettings ++ uniformDependencySettings

  lazy val core = Project(
    id = "core",
    base = file("core"),
    settings =
      standardSettings ++
        uniform.project("humbug-core", "au.com.cba.omnia") ++
        Seq[Sett](
          libraryDependencies ++= 
            Seq(
              "com.twitter"      %% "scrooge-core"        % "depend.versions.scrooge",
              "org.apache.thrift" % "libthrift"           % "0.8.0" % "provided"
            )
        )
  )

  lazy val generator = Project(
    id = "generator",
    base = file("generator"),
    settings =
      standardSettings ++
      uniform.project("humbug-generator", "au.com.cba.omnia") ++
      (uniformAssemblySettings: Seq[Sett]) ++
      inConfig(Test)(thriftSettings) ++
      Seq[Sett](
        libraryDependencies ++= depend.scalaz() ++ depend.testing() ++
          Seq(
            "com.twitter"      %% "scrooge-generator"  % "depend.versions.scrooge",
            "com.twitter"      %% "bijection-scrooge"  % "depend.versions.bijection"                        % "test",
            "au.com.cba.omnia" %% "omnia-test"         % "2.1.0-20140604032817-d3b19f6" % "test"
          )
      )
  ).dependsOn(core)

  lazy val plugin = Project(
    id = "plugin",
    base = file("plugin"),
    settings =
      standardSettings ++
      uniform.project("humbug-plugin", "au.com.cba.omnia")
  ).settings(
    sbtPlugin := true
  ).dependsOn(generator)

  val thriftSettings: Seq[Sett] = Seq(
    compileThrift <<= (
      streams,
      baseDirectory,
      fullClasspath in Runtime,
      sourceManaged
    ) map { (out, base, cp, outputDir) =>
      val files = (s"find ${base.getAbsolutePath} -name *.thrift" !!).split("\n")
      val cmd = s"java -cp ${cp.files.absString} au.com.cba.omnia.humbug.Main ${outputDir.getAbsolutePath} ${files.mkString(" ")}"
      out.log.info(cmd)
      cmd ! out.log

      (outputDir ** "*.scala").get.toSeq
    },
    sourceGenerators <+= compileThrift
  )
}
