import sbt._
import Keys._

object DemoCommand {

  // A simple, multiple-argument command that prints "Hi, " followed by the arguments.
  // It leaves the current state unchanged. The args are already parsed into a Seq[String].
  def demoCommand = Command.args("demo", "<args>") { (state, args) =>
    println("Hi, " + args.mkString(" "))
    state
  }
}
