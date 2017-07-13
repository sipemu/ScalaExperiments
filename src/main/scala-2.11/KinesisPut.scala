import com.typesafe.config.ConfigFactory

import DataGenerator.KinesisWordGenerator

/**
  * Created by info on 13.07.2017.
  */
object KinesisPut extends App {

  // load configuration
  val config = ConfigFactory.load()
  val awsAccessKeyId = config.getString("kinesis.awsAccessKeyId")
  val awsSecretKey = config.getString("kinesis.awsSecretKey")
  val stream = config.getString("kinesis.stream")
  val endpoint = config.getString("kinesis.endpoint")
  val recordsPerSecond = config.getInt("kinesis.recordsPerSecond")
  val wordsPerRecord = config.getInt("kinesis.wordsPerRecord")

  // words
  val words = List("this", "is", "a", "kinesis", "example")

  KinesisWordGenerator.wordGenerator(awsAccessKeyId, awsSecretKey, stream, endpoint, recordsPerSecond, wordsPerRecord, words)
}
