package example

import gigahorse.support.okhttp.Gigahorse
import gigahorse.{FullResponse, HttpClient, Request}
import play.api.libs.json.{JsValue, Json}

import scala.concurrent.{ExecutionContext, Future}

object Weather {

  def weatherOf(locationName: String, longFormat: Boolean = false)(implicit ec: ExecutionContext): Future[String] = {

    val baseUrl: String = "https://www.metaweather.com/api/location"

    lazy val http: HttpClient = Gigahorse.http(Gigahorse.config)

    val locationRequest: Request = Gigahorse.url(baseUrl + "/search/").get.addQueryString("query" -> locationName)

    def weatherRequest(woeid: Int): Request =
      Gigahorse.url(baseUrl + "/%s/" format woeid).get

    val future = for {
      location <- http.run(locationRequest, parse)
      woeid = (location \ 0  \ "woeid").get.as[Int]
      fullWeatherJson <- http.run(weatherRequest(woeid), parse)
    } yield {
      if (longFormat)
        Json.prettyPrint(fullWeatherJson)
      else
        (fullWeatherJson \\ "weather_state_name").head.as[String].toLowerCase
    }

    future andThen { case _ => http.close }
  }

  private def parse: FullResponse => JsValue = Gigahorse.asString andThen Json.parse
}
