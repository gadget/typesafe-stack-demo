package controllers

import play.api._
import akka.util.Timeout
import akka.pattern.ask
import play.api.mvc._
import akka.actor._
import akka.util.duration._
import akka.routing.FromConfig
import com.greenformatics.typesafe.actors._
import play.api.mvc.Results._
import play.api.libs.concurrent._

object Application extends Controller {
  
  val system = ActorSystem("pi")
  val router = system.actorOf(Props[Calculator].withRouter(FromConfig()), "balancer")
  
  def index = Action {
    Ok(views.html.index())
  }

  def compute(start: Int, elements: Int) = Action {
    AsyncResult {
      implicit val timeout= Timeout(5.seconds)
      (router ? Work(start, elements) ).mapTo[Double].asPromise.map { result =>
        Ok(views.html.computingResult(start, elements, result))
      }
    }
  }
  
}
