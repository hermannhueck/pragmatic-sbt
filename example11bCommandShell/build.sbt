ThisBuild / scalaVersion     := "2.12.8"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

import ShellCommand._

lazy val root = (project in file("."))
  .settings(
    name := "Example11b",
    commands += shellCommand,
    // commands += shellCommandAlt,
  )
