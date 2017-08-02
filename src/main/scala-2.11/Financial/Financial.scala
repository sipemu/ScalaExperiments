package Financial

import org.apache.spark.sql.{DataFrame, Dataset}
import org.apache.spark.sql.functions.{asc, col, lead}
import org.apache.spark.sql.expressions.Window

/**
  * Created by info on 08.07.2017.
  */
class Financial extends Serializable {

  def calcReturn[T](df: Dataset[T], partition: String, date: String, price: String): DataFrame = {
    // Define Window Function for Calculating log return by Firm
    val window = Window.partitionBy(partition).orderBy(asc(date))
    val logReturn = org.apache.spark.sql.functions.log(col(price) / lead(col(price), -1).over(window))

    // Calculate log return
    df.withColumn("returns", logReturn)
  }

  def movingAverage[T](df: Dataset[T], partition: String, date: String, price: String, start: Int): DataFrame = {
    val window = Window.partitionBy(partition).orderBy(asc(date)).rowsBetween(start, -1)
    val avg = org.apache.spark.sql.functions.avg(col(price)).over(window)

    // calculate moving window
    df.withColumn("movingAverage", avg)
  }


  def cumulativeSum[T](df: Dataset[T], partition: String, date: String, price: String): DataFrame = {
    val window = Window.partitionBy(partition).orderBy(asc(date))
    val cumSum = org.apache.spark.sql.functions.sum(col(price)).over(window)

    // calculate cumulative average
    df.withColumn("cumulativeSum", cumSum)
  }
}
