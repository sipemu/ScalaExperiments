import Financial.Financial
import Models.{FinanceSchemas, FinancialData}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{DataTypes, StructField, StructType}
import org.apache.spark.sql.functions.monotonically_increasing_id

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
    .master("local[*]")
    .appName("Return Calculation")
    .getOrCreate()

  import spark.implicits._
  val dataFileSchema = FinanceSchemas.estSchema()

  // Read Data File with Specified Date Format
  val priceData = spark.read
    .option("timestampFormat", "dd.MM.yyyy")
    .option("delimiter", ";")
    .option("header", false)
    .schema(dataFileSchema)
    .csv("./data/stockprice.csv")
    .as[FinancialData]
    .persist()

  // Return Calculation
  val financial = new Financial
  val returnData = financial.calcReturn(priceData, "firm", "date", "price")
  returnData.coalesce(1)
    .write
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
  val cumSum = financial.cumulativeSum(returnData, "firm", "date", "returns")
  cumSum.write
    .option("delimiter", ";")
    .option("header", false)
    .csv("./data/stockprice_with_cumulativeSum.csv")

  // Stop Session
  spark.stop()
}
