name := "ScalaExperiments"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.1.0",
  "org.apache.spark" %% "spark-mllib" % "2.1.0",
  "org.apache.spark" %% "spark-streaming" % "2.1.0",
  "org.apache.spark" %% "spark-streaming-kinesis-asl" % "2.1.0",
  "com.typesafe"     % "config" % "1.2.0",
  "com.amazonaws"    %  "aws-java-sdk-kinesis" % "1.11.160",
  "com.amazonaws"    % "amazon-kinesis-client" % "1.7.3",
  "com.typesafe.play" %% "play-json" % "2.5.1",
  "org.scalaj"        %% "scalaj-http" % "2.3.0",
  "org.ddahl" %% "rscala" % "2.2.2"
)

