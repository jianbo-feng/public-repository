package scala

object MethodAndFunctionDemo {

  //定义一个方法
  //方法 m1 参数要求是一个函数，函数的参数必须是两个Int类型
  //返回值类型也是Int类型
  def m1(f:(Int, Int) => Int) : Int = {
    f(2, 6)
  }

  //定义一个函数f1,参数是两个Int类型，返回值是一个Int类型
  val f1 = (x:Int,y:Int) => x + y
  //再定义一个函数f2
  val f2 = (m:Int,n:Int) => m * n

  def main(args : Array[String]): Unit = {
    val r1 = m1(f1)
    println(r1)

    val r2 = m1(f2)
    println(r2)

    // 定义整型 List
    val x1 = List(1,2,3,4)

    // 定义 Set
    val x2 = Set(1,3,5,7)

    // 定义 Map
    val x3 = Map("one" -> 1, "two" -> 2, "three" -> 3)

    // 创建两个不同类型元素的元组
    val x4 = (10, "Runoob")

    // 定义 Option
    val x5:Option[Int] = Some(5)
    println(x3)
  }
}
