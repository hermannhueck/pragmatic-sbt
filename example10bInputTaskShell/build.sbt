ThisBuild / scalaVersion     := "2.12.8"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "Example10b",
  )

lazy val shellTask = inputKey[Int]("Execute a shell command")

shellTask := {
  
  import complete.DefaultParsers._
  import scala.language.postfixOps
  import scala.sys.process._
  
  val args: Seq[String] = spaceDelimited("<arg>").parsed
  val commandLine = args.mkString(" ")
  Process(Seq("/bin/sh", "-c", commandLine)) !
}

addCommandAlias("sh", "shellTask")