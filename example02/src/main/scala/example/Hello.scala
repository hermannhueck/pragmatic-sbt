package example

object Hello extends Greeting with App {
  println(s"\n$greeting\n")
}

trait Greeting {
  lazy val greeting: String = "hello"
}
