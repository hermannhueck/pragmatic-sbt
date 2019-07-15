ThisBuild / scalaVersion     := "2.12.8"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "Example10a",
  )

val demo = inputKey[Unit]("A demo input task.")

demo := {
  
  import complete.DefaultParsers._
  
  println(">>> Current Scala version:")
  println(scalaVersion.value) // access a setting

  val args: Seq[String] = spaceDelimited("<arg>").parsed // get parsed result
  println(">>> Arguments to demo:")
  args foreach println
}
