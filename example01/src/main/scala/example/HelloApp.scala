package example

trait Greeting {
    val greeting = "Hello World!"
}

object HelloApp extends App with Greeting {
    println(s"\n$greeting\n")
}
