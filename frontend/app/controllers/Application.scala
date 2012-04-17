package controllers

import akka.actor._
import akka.pattern.ask
import akka.routing.FromConfig
import akka.util.duration.intToDurationInt
import akka.util.Timeout
import calculator._
import play.api.libs.concurrent.akkaToPlay
import play.api.mvc._

object Application extends Controller {

  val system = ActorSystem("pi")
  val router = system.actorOf(Props[Calculator].withRouter(FromConfig()), "balancer")

  def index = Action {
    Ok(views.html.index())
  }

  def compute(start: Int, elements: Int) = Action {
    AsyncResult {
      implicit val timeout = Timeout(5.seconds)
      (router ? Work(start, elements)).mapTo[Double].asPromise.map { result =>
        Ok(views.html.computingResult(start, elements, result))
      }
    }
  }

}