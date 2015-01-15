name := """fix-it"""

version := "1.0-SNAPSHOT"

javaOptions ++= Seq("-Xmx512M", "-Xmx2048M", "-XX:MaxPermSize=2048M")

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  cache,
  javaWs,
  "org.mongojack" % "play-mongojack_2.10" % "2.0.0-RC2" ,
  "com.google.inject" % "guice" % "3.0"
)


