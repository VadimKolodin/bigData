ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "lr4"
  )

libraryDependencies += "org.apache.zookeeper" % "zookeeper" % "3.8.0"
