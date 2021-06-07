import Dependencies._
import org.irundaia.sbt.sass._

name := "physics_interactive_platform"
 
version := "1.0"

SassKeys.cssStyle := Maxified

SassKeys.generateSourceMaps := false

SassKeys.syntaxDetection := ForceScss

includeFilter in(Assets, LessKeys.less) := "*.less"
excludeFilter in(Assets, LessKeys.less) := "_*.less"

lazy val `physics_interactive_platform` = (project in file(".")).enablePlugins(PlayScala)

scalacOptions ++= CompilerOptions.cOptions

Global / onChangedBuildSource := ReloadOnSourceChanges

scalaVersion := "2.13.1"

addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.11.0" cross CrossVersion.full)

resolvers ++= Seq(
  "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases",
  "Akka Snapshot Repository" at "https://repo.akka.io/snapshots/",
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots")
)

libraryDependencies ++= rootDependencies ++ Seq(jdbc, ehcache, ws, specs2 % Test, guice)