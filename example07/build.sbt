import Dependencies._

val scala212 = "2.12.8"
val scala213 = "2.13.0"
val supportedScalaVersions = List(scala212, scala213)

ThisBuild / scalaVersion     := scala213
ThisBuild / version          := "0.1.0"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .aggregate(app, lib)
  .settings(
    name := "Example07",
    publish / skip := true, // no code to publish
    crossScalaVersions := Nil,
  )

lazy val app = (project in file("app"))
  .dependsOn(lib)
  .settings(
    name := "WeatherApp",
    publish / skip := true,  // no need to publish the app
    crossScalaVersions := supportedScalaVersions,
    initialCommands :=
      """
        |import scala.concurrent._
        |import scala.concurrent.duration._
        |import scala.concurrent.ExecutionContext.Implicits.global
        |import libWeather.Weather._
        |""".stripMargin,
  )

lazy val lib = (project in file("lib"))
  .settings(
    name := "WeatherLib",
    // don't skip publishing in the library project
    crossScalaVersions := supportedScalaVersions,
    libraryDependencies ++= Seq(okHttp, playJson, scalaTest % Test),
  )
