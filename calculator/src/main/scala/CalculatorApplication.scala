package calculator

import akka.actor.ActorSystem
import akka.actor.Props
import akka.kernel.Bootable

class CalculatorApplication extends Bootable {

  val system = ActorSystem("calculatorActorSystem")

  def startup() {
    system.actorOf(Props[Calculator], "calculator")
  }

  def shutdown() {
    system.shutdown()
  }

}

object CalculatorApplication {

  def main(args: Array[String]) {
    new CalculatorApplication
    println("Started Calculator Application - waiting for messages")
  }

}