
lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.6",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "maryana-server",

  )

// META-INF discarding
assemblyMergeStrategy in assembly := {
  case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
  case x => MergeStrategy.last
}


assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)
mainClass in assembly := Some("org.zella.maryanaserver.Runner")

libraryDependencies += "com.google.cloud" % "google-cloud-datastore" % "1.40.0"
// https://mvnrepository.com/artifact/com.googlecode.objectify/objectify
libraryDependencies += "com.googlecode.objectify" % "objectify" % "6.0.1"
// https://mvnrepository.com/artifact/io.vertx/vertx-web
libraryDependencies += "io.vertx" % "vertx-web" % "3.5.3"
