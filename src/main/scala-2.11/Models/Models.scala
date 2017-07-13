package Models

import org.apache.spark.sql.types.{DataTypes, StructField, StructType}

/**
  * Created by info on 12.07.2017.
  */

case class FinancialData(firm: String, date: java.sql.Timestamp, price: Double)

object FinanceSchemas {
  def estSchema(): StructType = {
    val firm = StructField("firm", DataTypes.StringType)
    val date = StructField("date", DataTypes.TimestampType)
    val price = StructField("price", DataTypes.DoubleType)
    val dataFields = Array(firm, date, price)
    StructType(dataFields)
  }

}