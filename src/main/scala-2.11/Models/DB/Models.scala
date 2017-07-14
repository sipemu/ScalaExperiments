package Models.DB

/**
  * Created by info on 14.07.2017.
  */
case class Facility(equipmentnumber: Int,
                    eqType: String,
                    description: Option[String],
                    geoX: Option[Double],
                    geoY: Option[Double],
                    state: String,
                    stationNumber: Int)

case class Disruption(equipmentnumber: Int,
                      eqType: String,
                      description: Option[String],
                      geoX: Option[Double],
                      geoY: Option[Double],
                      state: String,
                      stationNumber: Int)
