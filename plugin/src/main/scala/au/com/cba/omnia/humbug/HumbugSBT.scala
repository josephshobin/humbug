package au.com.cba.omnia.humbug

import sbt._
import Keys._

import java.io.File
import scala.collection.JavaConverters._

/**
  * This is pretty much a copy of the twitter scrooge sbt plugin https://github.com/twitter/scrooge
  * modified for Humbug.
  */
object HumbugSBT extends Plugin {
  def compile(
    log: Logger,
    outputDir: File,
    thriftFiles: Set[File]
  ) {
    Main.generate(outputDir.getAbsolutePath, thriftFiles.map(_.getAbsolutePath).toList)
  }

  def filter(dependencies: Classpath, whitelist: Set[String]): Classpath = {
    dependencies.filter { dep =>
      val module = dep.get(AttributeKey[ModuleID]("module-id"))
      module.map { m =>
        whitelist.contains(m.name)
      }.getOrElse(false)
    }
  }

  val humbugThriftDependencies = SettingKey[Seq[String]](
    "humbug-thrift-dependencies",
    "artifacts to extract and compile thrift files from"
  )

  val humbugThriftSourceFolder = SettingKey[File](
    "humbug-thrift-source-folder",
    "directory containing thrift source files"
  )

  val humbugThriftExternalSourceFolder = SettingKey[File](
    "humbug-thrift-external-source-folder",
    "directory containing external source files to compile"
  )

  val humbugThriftSources = TaskKey[Seq[File]](
    "humbug-thrift-sources",
    "complete list of thrift source files to compile"
  )

  val humbugThriftOutputFolder = SettingKey[File](
    "humbug-thrift-output-folder",
    "output folder for generated scala files (defaults to sourceManaged)"
  )

  val humbugIsDirty = TaskKey[Boolean](
    "humbug-is-dirty",
    "true if humbug has decided it needs to regenerate the scala files from thrift sources"
  )

  val humbugUnpackDeps = TaskKey[Seq[File]](
    "humbug-unpack-deps",
    "unpack thrift files from dependencies, generating a sequence of source directories"
  )

  val humbugGen = TaskKey[Seq[File]](
    "humbug-gen",
    "generate scala code from thrift files using humbug"
  )

  // Dependencies included in the `thrift` configuration will be used
  // in both compile and test.
  val thriftConfig = config("thrift")

  /**
   * these settings will go into both the compile and test configurations.
   * you can add them to other configurations by using inConfig(<config>)(genThriftSettings),
   * e.g. inConfig(Assembly)(genThriftSettings)
   */
  val genThriftSettings: Seq[Setting[_]] = Seq(
    humbugThriftSourceFolder <<= (sourceDirectory) { _ / "thrift" },
    humbugThriftExternalSourceFolder <<= (target) { _ / "thrift_external" },
    humbugThriftOutputFolder <<= (sourceManaged) { identity },
    humbugThriftDependencies := Seq(),
    humbugIsDirty := true,

    // complete list of source files
    humbugThriftSources <<= (
      humbugThriftSourceFolder,
      humbugUnpackDeps
    ) map { (srcDir, extDirs) =>
      (Seq(srcDir) ++ extDirs).flatMap { dir => (dir ** "*.thrift").get }
    },

    // unpack thrift files from all dependencies in the `thrift` configuration
    //
    // returns Seq[File] - directories that include thrift files
    humbugUnpackDeps <<= (
      streams,
      configuration,
      classpathTypes,
      update,
      humbugThriftDependencies,
      humbugThriftExternalSourceFolder
    ) map { (out, configuration, cpTypes, update, deps, extDir) =>
      IO.createDirectory(extDir)
      val whitelist = deps.toSet
      val dependencies =
        Classpaths.managedJars(thriftConfig, cpTypes, update) ++
        filter(Classpaths.managedJars(configuration, cpTypes, update), whitelist)

      val paths = dependencies.map { dep =>
        val module = dep.get(AttributeKey[ModuleID]("module-id"))
        module.flatMap { m =>
          val dest = new File(extDir, m.name)
          IO.createDirectory(dest)
          val thriftFiles = IO.unzip(dep.data, dest, "*.thrift").toSeq
          if (thriftFiles.isEmpty) {
            None
          } else {
            Some(dest)
          }
        }
      }
      paths.filter(_.isDefined).map(_.get)
    },

    // actually run humbug
    humbugGen <<= (
      streams,
      humbugIsDirty,
      humbugThriftSources,
      humbugThriftOutputFolder
    ) map { (out, isDirty, sources, outputDir) =>
      // for some reason, sbt sometimes calls us multiple times, often with no source files.
      if (isDirty && !sources.isEmpty) {
        out.log.info("Generating humbug thrift for %s ...".format(sources.mkString(", ")))
        compile(out.log, outputDir, sources.toSet)
      }
      (outputDir ** "*.scala").get.toSeq
    },
    sourceGenerators <+= humbugGen
  )

  val humbugSettings =
    Seq(ivyConfigurations += thriftConfig) ++
    inConfig(Test)(genThriftSettings) ++
    inConfig(Compile)(genThriftSettings)
}
