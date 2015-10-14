name := """fix-it"""

version := "1.0-SNAPSHOT"

javaOptions ++= Seq("-Xmx512M", "-Xmx2048M", "-XX:MaxPermSize=2048M")

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  cache,
  javaWs,
  javaCore,
  javaJpa,
    "org.springframework" % "spring-context" % "4.0.4.RELEASE",
  	"javax.inject" % "javax.inject" % "1",
  	"org.springframework.data" % "spring-data-mongodb" % "1.7.2.RELEASE",
  	"org.springframework" % "spring-test" % "4.0.4.RELEASE" % "test",
    "org.mongojack" % "play-mongojack_2.10" % "2.0.0-RC2" ,
    "com.amazonaws" % "aws-java-sdk" % "1.3.11" ,
    "com.google.apis" % "google-api-services-plus" % "v1-rev219-1.20.0" ,
  	"com.google.inject" % "guice" % "3.0",
  	"org.atmosphere" % "atmosphere-play" % "2.1.2" 	
  )
