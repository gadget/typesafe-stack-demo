package com.greenformatics.typesafe

import akka.kernel.Bootable
import akka.actor.{ Props, Actor, ActorSystem }
import com.typesafe.config.ConfigFactory
import com.greenformatics.typesafe.actors._

class CalculatorApplication extends Bootable {

  val system = ActorSystem("CalculatorApplication")
  val actor = system.actorOf(Props[Calculator], "calculator")

  def startup() {
  }

  def shutdown() {
    system.shutdown()
  }

}

object CalcApp {

  def main(args: Array[String]) {
    new CalculatorApplication
    println("Started Calculator Application - waiting for messages")
  }

}
