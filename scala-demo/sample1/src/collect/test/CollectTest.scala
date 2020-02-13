package collect.test

/**
  * 集合测试
  */
object CollectTest {

  def main(args: Array[String]): Unit = {
    println(1)

    var list: List[Any] = List(1, 2, 3, "111")
    list = list :+ "aaaa" // 加尾部
    list = "新年"::list // 加在头部
    val list2 = list +: "hello" // 加尾部，并对“hello”进行字符串分割
    println(list)
    println(list2)
  }

}
