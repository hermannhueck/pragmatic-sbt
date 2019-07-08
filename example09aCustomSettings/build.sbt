val sampleStringSetting: SettingKey[String] = settingKey[String]("A sample string setting.")
val sampleIntSetting: SettingKey[Int] = settingKey[Int]("A sample int setting.")

ThisBuild / organization := "com.example"
ThisBuild / version      := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.13.0"

lazy val root = (project in file("."))
  .settings(
    name := "Example9a",

    sampleStringSetting := {
      println(">>> Executing sampleStringSetting ...")
      System.getProperty("java.home")
    },

    sampleIntSetting := {
      println(">>> Executing sampleIntSetting ...")
      val sum = 1 + 2
      println("sum: " + sum)
      sum
    },
  )