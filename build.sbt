name := "httpapi"

version := "0.1"

scalaVersion := "2.12.4"

scalacOptions ++= Seq("-Ypartial-unification")


val LogbackVersion = "1.2.3"



libraryDependencies ++= Seq(
  "com.twitter" %% "finatra-http" % "18.2.0",
  "ch.qos.logback" % "logback-classic" % LogbackVersion,
  "org.tpolecat" %% "doobie-core" % "0.5.1",
  "com.twitter" %% "finatra-http" % "18.2.0" % "test" classifier "tests"
)