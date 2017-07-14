name := "ScalaExperiments"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.1.0",
  "org.apache.spark" %% "spark-mllib" % "2.1.0",
  "org.apache.spark" %% "spark-streaming" % "2.1.0",
  "org.apache.spark" %% "spark-streaming-kinesis-asl" % "2.1.0",
  "com.typesafe"     % "config" % "1.2.0",
  "com.amazonaws"    % "amazon-kinesis-client" % "1.7.6"
)

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.3.6")