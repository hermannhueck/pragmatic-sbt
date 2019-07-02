package weather

import org.scalatest._
import play.api.libs.json.{JsValue, Json}

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

class WeatherSpec extends FlatSpec with Matchers {
  "A weather request (in long format)" should "provide a JSON result as String" in {
    val weather = Await.result(WeatherLib.weatherOf("Berlin", longFormat = true), 10.seconds)
    val fullJson: JsValue = Json.parse(weather)
    //println(s"\n${Json.prettyPrint(fullJson)}\n")
    val seqJsValues: Seq[JsValue] = fullJson \\ "weather_state_name"
    //println(s"\n$seqJsValues\n")
    seqJsValues.length shouldBe > (0)
  }
}
