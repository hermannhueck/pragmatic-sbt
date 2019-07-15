import Dependencies._
import sbt.Def

ThisBuild / scalaVersion     := "2.12.7"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "ExamplesInputTask",
    libraryDependencies += scalaTest % Test
  )

//------------------------------

import complete.DefaultParsers._
import complete.Parser

val parser: Def.Initialize[State => Parser[(String,String)]] =
  Def.setting {
    (state: State) =>
      ( token("scala" <~ Space) ~ token(scalaVersion.value) ) |
        ( token("sbt" <~ Space) ~ token(sbtVersion.value) ) |
        ( token("commands" <~ Space) ~
          token(state.remainingCommands.size.toString) )
  }

val demo = inputKey[Unit]("A demo input task.")

demo := {
  val (tpe, value) = parser.parsed
  println("Type: " + tpe)
  println("Value: " + value)
  println("Packaged: " + (Compile/packageBin).value.getAbsolutePath)
}

//------------------------------

val run2 = inputKey[Unit](
  "Runs the main class twice with different argument lists separated by --")

val separator: Parser[String] = "--"

run2 := {
  val one = (run in Compile).evaluated
  val sep = separator.parsed
  val two = (run in Compile).evaluated
}

lazy val run2a = inputKey[Unit]("Runs the main class twice: " +
  "once with the project name and version as arguments" +
  "and once with command line arguments preceded by hard coded values.")

// The argument string for the first run task is ' <name> <version>'
lazy val firstInput =
  Def.setting(s" ${name.value} ${version.value}")

// Make the first arguments to the second run task ' red blue'
lazy val secondInput: String = " red blue"

run2a := {
  //val one2a = (Compile/run).fullInput(firstInput.value).evaluated
  val two2a = (Compile/run).partialInput(secondInput).evaluated
}

//------------------------------

lazy val runFixed = taskKey[Unit]("A task that hard codes the values to `run`")

runFixed := {
  val _ = (run in Compile).toTask(" blue green").value
  println("Done!")
}

//------------------------------

lazy val runFixed2 = taskKey[Unit]("A task that hard codes the values to `run`")

fork in run := true

runFixed2 := {
  val x = (run in Compile).toTask(" blue green").value
  val y = (run in Compile).toTask(" red orange").value
  println("Done!")
}
