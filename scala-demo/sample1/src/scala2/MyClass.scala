package scala2

class MyClass(xc: Int, yc: Int) extends Equal {

  var x: Int = xc
  var y: Int = yc

  override def isEqual(obj: Any): Boolean =
    obj.isInstanceOf[MyClass] &&
      obj.asInstanceOf[MyClass].x == x &&
      obj.asInstanceOf[MyClass].y == y

  override def isNotEqual(obj: Any): Boolean =
   obj == null || !obj.isInstanceOf[MyClass]
}
