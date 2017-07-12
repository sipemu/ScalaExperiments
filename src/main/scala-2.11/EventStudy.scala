import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{DataTypes, StructField, StructType}
import org.apache.spark.sql.functions._

import Models.financialData
/**
  * Created by info on 08.07.2017.
  */
object EventStudy extends App {
  val spark = SparkSession.builder()
    .master("[*]")
    .appName("Event Study")
    .getOrCreate()

  // Create file schema
  val firm = StructField("firm", DataTypes.StringType)
  val date = StructField("date", DataTypes.CalendarIntervalType)
  val price = StructField("price", DataTypes.DoubleType)
  val dataFields = Array(firm, date, price)
  val dataFileSchema = StructType(dataFields)

  import spark.implicits._

  // Read Data File with Specified Date Format
  val priceData = spark.read
    .option("dateFormat", "dd.MM.yyyy")
    .option("delimiter", ";")
    .option("header", false)
    .schema(dataFileSchema)
    .csv("./data/stockprice.csv")
    .repartition(col("firm"))
    .as[financialData]
    .persist()

  priceData.mapPartitions()

  spark.stop()
}
