val sampleStringTask: TaskKey[String] = taskKey[String]("A sample string task.")
val sampleIntTask: TaskKey[Int] = taskKey[Int]("A sample int task.")

ThisBuild / organization := "com.example"
ThisBuild / version      := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.13.0"

lazy val root = (project in file("."))
  .settings(
    name := "Example9b",

    sampleStringTask := {
      println(">>> Executing sampleStringTask ...")
      System.getProperty("java.home")
    },

    sampleIntTask := {
      println(">>> Executing sampleIntTask ...")
      val sum = 1 + 2
      println("sum: " + sum)
      sum
    },
  )