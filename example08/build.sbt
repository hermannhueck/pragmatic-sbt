import Dependencies._

val scala212 = "2.12.8"
val scala213 = "2.13.0"
val supportedScalaVersions = List(scala212, scala213)

inThisBuild(
  Seq(
    scalaVersion       := scala213,
    crossScalaVersions := supportedScalaVersions,
    version            := "0.1.0",
    organization       := "com.example",
    organizationName   := "example",
    maintainer         := "functional.hacker@example.com"
  )
)  

lazy val root = (project in file("."))
  .aggregate(app, lib)
  .enablePlugins(JavaAppPackaging)
  .settings(
    name := "Example08",
    crossScalaVersions := Nil,
    publish / skip := true,
  )

lazy val app = (project in file("app"))
  .dependsOn(lib)
  .enablePlugins(JavaAppPackaging)
  .settings(
    name := "WeatherApp",
    maintainer := (ThisBuild / maintainer).value,
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
  .enablePlugins(JavaAppPackaging)
  .settings(
    name := "WeatherLib",
    maintainer := (ThisBuild / maintainer).value,
    crossScalaVersions := supportedScalaVersions,
    libraryDependencies ++= Seq(
      okHttp,
      playJson,
      scalaTest % Test,
    ),
  )
