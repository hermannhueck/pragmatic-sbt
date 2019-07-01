import Dependencies._

ThisBuild / scalaVersion     := "2.12.8"
ThisBuild / version          := "0.1.0"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "Example03",
    libraryDependencies ++= Seq(
      okHttp,
      playJson,
      scalaTest % Test,
    )
  )
