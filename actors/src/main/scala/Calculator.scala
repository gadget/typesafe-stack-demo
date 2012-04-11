package com.greenformatics.typesafe.actors

import akka.actor.Actor
import Actor._

case class Work(start: Int, nrOfElements: Int)

class Calculator extends Actor {

  def calculatePiFor(start: Int, nrOfElements: Int): Double = {
    println("calculating...")
    var acc = 0.0
    for (i <- start until (start + nrOfElements))
      acc += 4.0 * (1 - (i % 2) * 2) / (2 * i + 1)
    acc
  }

  def receive = {
    case Work(start, nrOfElements) => sender ! (calculatePiFor(start, nrOfElements))
  }

}