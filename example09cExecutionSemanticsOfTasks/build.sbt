val startServer = taskKey[Unit]("start server")
val stopServer = taskKey[Unit]("stop server")
val sampleIntTask = taskKey[Int]("A sample int task.")
val sampleStringTask = taskKey[String]("A sample string task.")

ThisBuild / organization := "com.example"
ThisBuild / version      := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.13.0"

lazy val root = (project in file("."))
  .settings(
    name := "Example9c",
    startServer := {
      println("starting...")
      Thread.sleep(500)
    },
    stopServer := {
      println("stopping...")
      Thread.sleep(500)
    },
    sampleIntTask := {
      startServer.value // This line is invoked BEFORE the other statements of the task
      val sum = 1 + 2
      println("sum: " + sum)
      stopServer.value  // This line is invoked BEFORE the other statements of the task
      sum
    },
    sampleStringTask := {
      startServer.value // This line is invoked BEFORE the other statements of the task
      val s = sampleIntTask.value.toString // This line is invoked BEFORE the other statements of the task
      println("s: " + s)
      s
    }
  )