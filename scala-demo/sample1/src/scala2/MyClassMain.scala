package scala2

object MyClassMain {

  def main(args: Array[String]): Unit = {
    val instance1 = new MyClass(2, 3)
    val instance2 = new MyClass(3, 4);
    val instance3 = new MyClass(2, 4)

    println(instance1.isEqual(instance2))
    println(instance1.isNotEqual(instance2))
    println(instance1.isEqual(instance3))
  }
}
