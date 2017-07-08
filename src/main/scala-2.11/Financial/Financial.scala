package Financial

import breeze.numerics.log
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.{lead, col, asc}
import org.apache.spark.sql.expressions.Window

/**
  * Created by info on 08.07.2017.
  */
class Financial extends Serializable {

  def calcReturn(df: DataFrame, partition: String, date: String, price: String): DataFrame = {
    // Define Window Function for Calculating log return by Firm
    val window = Window.partitionBy(partition).orderBy(asc(date))
    val logReturn = log(col(price) / lead(col(price), -1).over(window))

    // Calculate log return
    df.withColumn("return", logReturn)
  }

  def movingAverage(df: DataFrame, partition: String, date: String, price: String, start: Int): DataFrame = {
    val window = Window.partitionBy(partition).orderBy(asc(date)).rangeBetween(start, -1)
    val avg = org.apache.spark.sql.functions.avg(col(price)).over(window)

    // calculate moving window
    df.withColumn("movingAverage_" + - 1 * start, avg)
  }

  def cumulativeSum(df: DataFrame, partition: String, date: String, price: String): DataFrame = {
    val window = Window.partitionBy(partition).orderBy(asc(date))
    val cumSum = org.apache.spark.sql.functions.sum(col(price)).over(window)

    // calculate cumulative average
    df.withColumn("cumulativeSum", cumSum)
  }
}
