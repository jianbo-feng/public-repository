package scala

class Point(xc: Int, yc: Int) {

  var x: Int = xc
  var y: Int = yc

  def move(dx: Int, dy: Int): Unit = {
    x = x + dx
    y = y + dy
    println("x 的坐标点：" + x)
    println("y 的坐标点：" + y)
  }
}

object Test2 {
  def main(args: Array[String]): Unit = {
    val point = new Point(10, 20)

    // 移动到一个新位置
    point.move(10, 10)
  }
}