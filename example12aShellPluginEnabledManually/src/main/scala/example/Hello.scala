package example

object Hello extends Greeting with App {
  args foreach println
}

trait Greeting {
  lazy val greeting: String = "hello"
}
