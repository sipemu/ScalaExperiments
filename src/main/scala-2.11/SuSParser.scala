import API.SuSAPI
import com.typesafe.config.ConfigFactory

/**
  * Created by info on 14.07.2017.
  */
object SuSParser extends App {
  // load configuration
  val config = ConfigFactory.load()
  val url = config.getString("bahnDevAPI.url")
  val token = config.getString("bahnDevAPI.token")

  // init API object object
  val bahnAPI = SuSAPI(url = url, token = token)

  // get actual status of facilities
  val facilities = bahnAPI.getFacilities()

  // print facilities
  facilities.foreach(x => println(x))
}
