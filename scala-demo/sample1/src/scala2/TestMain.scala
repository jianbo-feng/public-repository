package scala2

import scala.Location

object TestMain {

  def main(args : Array[String]): Unit = {

    val location = new Location(20, 15, 40)

    location.move(30, 40, 35)
  }
}
