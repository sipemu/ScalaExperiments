package DataGenerator

import java.nio.ByteBuffer

import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.kinesis.AmazonKinesisClient
import com.amazonaws.services.kinesis.model.PutRecordRequest

import scala.util.Random

/**
  * Created by info on 13.07.2017.
  */
object KinesisWordGenerator {

  /*
  * Minimal adapteds from
  * https://github.com/apache/spark/blob/master/external/kinesis-asl/src/main/scala/org/apache/spark/examples/streaming/KinesisWordCountASL.scala
   */
  def wordGenerator(awsAccessKeyId: String,
                    awsSecretKey: String,
                    stream: String,
                    endpoint: String,
                    recordsPerSecond: Int,
                    wordsPerRecord: Int,
                    words: List[String]): Unit = {

    // TODO: DEPRECATED
    val awsCreds = new BasicAWSCredentials(awsAccessKeyId, awsSecretKey)
    val kinesisClient = new AmazonKinesisClient(new BasicAWSCredentials(awsAccessKeyId, awsSecretKey))
    kinesisClient.setEndpoint(endpoint)

    for (i <- 1 to 10) {
      // Generate recordsPerSec records to put onto the stream
      val records = (1 to recordsPerSecond.toInt).foreach { recordNum =>
        // Randomly generate wordsPerRecord number of words
        val data = (1 to wordsPerRecord.toInt).map(x => {
          // Get a random index to a word
          val randomWordIdx = Random.nextInt(words.size)
          val randomWord = words(randomWordIdx)
          randomWord
        }).mkString(" ")

        // Create a partitionKey based on recordNum
        val partitionKey = s"partitionKey-$recordNum"

        // Create a PutRecordRequest with an Array[Byte] version of the data
        val putRecordRequest = new PutRecordRequest().withStreamName(stream)
          .withPartitionKey(partitionKey)
          .withData(ByteBuffer.wrap(data.getBytes()))

        // Put the record onto the stream and capture the PutRecordResult
        val putRecordResult = kinesisClient.putRecord(putRecordRequest)
      }

      // Sleep for a second
      Thread.sleep(1000)
      println("Sent " + recordsPerSecond + " records")
    }
  }
}
