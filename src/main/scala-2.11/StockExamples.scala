import Financial.Financial

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{DataTypes, StructField, StructType}

/**
  * Stock Price Return Calculation
  *
  * Experiments with SQL Window Functions:
  *   - lead: return calculation
  *   - avg: moving average calculation
  *   - sum: cumulative sum
  *
  */
object StockExamples extends App {
  val spark = SparkSession.builder()
    .master("[1]")
    .appName("Return Calculation")
    .getOrCreate()

  // Create file schema
  val firm = StructField("firm", DataTypes.StringType)
  val date = StructField("date", DataTypes.DateType)
  val price = StructField("price", DataTypes.DoubleType)
  val dataFields = Array(firm, date, price)
  val dataFileSchema = StructType(dataFields)

  // Read Data File with Specified Date Format
  val priceData = spark.read
    .option("dateFormat", "dd.MM.yyyy")
    .option("delimiter", ";")
    .option("header", false)
    .schema(dataFileSchema)
    .csv("./data/stockprice.csv").persist()

  // Return Calculation
  val financial = new Financial
  val returnData = financial.calcReturn(priceData, "firm", "date", "price")
  returnData.write
    .option("delimiter", ";")
    .option("header", false)
    .csv("./data/stockprice_with_return.csv")

  // Moving Average
  val movingAverage = financial.movingAverage(priceData, "firm", "date", "price", -5)
  movingAverage.write
    .option("delimiter", ";")
    .option("header", false)
    .csv("./data/stockprice_with_movingaverage.csv")

  // Cumulative Sum
  val cumSum = financial.cumulativeSum(priceData, "firm", "date", "price")
  cumSum.write
    .option("delimiter", ";")
    .option("header", false)
    .csv("./data/stockprice_with_cumulativeSum.csv")

  // Stop Session
  spark.stop()
}
