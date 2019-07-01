import scala.concurrent._
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import libWeather.Weather._

val weather = Await.result(weatherOf("Berlin"), 10.seconds)
