package scala

object TestLocationMain {

  def main(args : Array[String]): Unit = {
    val loc = new Location(10, 20, 15)

    loc.move(10, 10, 5);
  }
}
