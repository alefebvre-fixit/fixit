name := """fix-it"""

version := "1.0-SNAPSHOT"

javaOptions ++= Seq("-Xmx512M", "-Xmx2048M", "-XX:MaxPermSize=2048M")

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  cache,
  javaWs,
  "uk.co.panaxiom" %% "play-jongo" % "0.7.1-jongo1.0",
    "org.mongojack" % "play-mongojack_2.10" % "2.0.0-RC2" ,
    "com.amazonaws" % "aws-java-sdk" % "1.3.11" ,
    "com.google.apis" % "google-api-services-plus" % "v1-rev219-1.20.0" ,
  	"com.google.inject" % "guice" % "3.0" 	
  )


