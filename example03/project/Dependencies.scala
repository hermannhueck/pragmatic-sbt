import sbt._

object Dependencies {
  lazy val playJson = "com.typesafe.play" %% "play-json" % "2.7.3"
  lazy val okHttp = "com.eed3si9n" %% "gigahorse-okhttp" % "0.5.0"
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"
}
