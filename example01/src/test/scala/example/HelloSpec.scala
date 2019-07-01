package example

import org.scalatest._

class HelloSpec extends FlatSpec with Matchers {
  "The HelloApp object" should "say 'Hello World!'" in {
    println(HelloApp.greeting)
    HelloApp.greeting shouldEqual "Hello World!"
  }
}
