package app

import scala.concurrent.{Await, ExecutionContext}
import scala.concurrent.duration._

class WeatherBase {

  import libWeather.Weather._

  implicit val ec: ExecutionContext = ExecutionContext.global

  def showWeatherOf(location: String): String = {
    val scalaVersion = util.Properties.versionString
    val weather = Await.result(weatherOf(location), 10.seconds)
    s"\nScala $scalaVersion:\nThe weather in $location is:   $weather\n"
  }
}
