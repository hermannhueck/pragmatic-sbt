package app

object WeatherApp extends WeatherBase with App {

  import scala.util.chaining._

  showWeatherOf("Berlin") tap println
}
