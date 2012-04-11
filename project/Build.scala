import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "typesafe-stack-demo"
    val appVersion      = "1.0"

    val appDependencies = Seq(
      // Add your project dependencies here,
    )

    val actors = Project(appName + "-actors", file("actors"))

    val calculator = Project(appName + "-calculator", file("calculator")).dependsOn(actors)

    val frontend = PlayProject(
        appName + "-frontend", appVersion, appDependencies, path = file("frontend"), mainLang = SCALA
    ).dependsOn(actors)

}
