import sbt._
import Keys._

object HelloPlugin extends AutoPlugin {

  object autoImport {
    val greeting = settingKey[String]("greeting")
    val hello = taskKey[Unit]("say hello")
  }

  import autoImport._
  override def trigger = allRequirements
  override lazy val buildSettings = Seq(greeting := "Hi!", hello := helloTask.value)
  lazy val helloTask = Def.task {
      println(greeting.value)
    }
}