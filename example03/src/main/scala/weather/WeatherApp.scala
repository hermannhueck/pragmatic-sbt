package weather

import scala.concurrent.{Await, ExecutionContext}
import scala.concurrent.duration._

object WeatherApp extends App {

  import WeatherLib._

  implicit val ec: ExecutionContext = ExecutionContext.global

  def showWeatherOf(location: String): String = {
    val weather = Await.result(weatherOf(location), 10.seconds)
    s"\nThe weather in $location is:   $weather\n"
  }

  println(showWeatherOf("Berlin"))
}
