package API

import Models.DB.Facility
import play.api.libs.functional.syntax._
import play.api.libs.json._

import scalaj.http._

/**
  * Created by info on 14.07.2017.
  */
case class SuSAPI(url: String, token: String) {
  implicit val facilityReads: Reads[Facility] = (
    (__ \ "equipmentnumber").read[Int] and
      (__ \ "type").read[String] and
      (__ \ "description").readNullable[String] and
      (__ \ "geocoordX").readNullable[Double] and
      (__ \ "geocoordY").readNullable[Double] and
      (__ \ "state").read[String] and
      (__ \ "stationnumber").read[Int]
    )(Facility.apply _)


  def getFacilities(): Seq[Facility] = {
    val response: HttpResponse[String] =
      Http(url + "facilities")
        .header("Authorization", "Bearer " + token).asString

    val facilityResult: JsResult[Seq[Facility]] = Json.parse(response.body)
      .validate[Seq[Facility]]

    facilityResult.get
  }
}
