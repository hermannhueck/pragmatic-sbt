ThisBuild / organization := "com.example"
ThisBuild / scalaVersion := "2.12.8"
ThisBuild / version      := "0.1.0-SNAPSHOT"

lazy val root = (project in file("."))
  // no need to enable ShellPlugin
  .settings(
    name := "Example12b",
  )
