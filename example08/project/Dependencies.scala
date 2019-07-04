import sbt._

object Versions {
  val playVersion = "2.7.+"
  val okHttpVersion = "0.5.+"
  val scalaTestVersion = "3.0.+"
}

object Dependencies {
  import Versions._
  lazy val playJson = "com.typesafe.play" %% "play-json" % playVersion
  lazy val okHttp = "com.eed3si9n" %% "gigahorse-okhttp" % okHttpVersion
  lazy val scalaTest = "org.scalatest" %% "scalatest" % scalaTestVersion
}
