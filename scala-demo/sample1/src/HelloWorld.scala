object HelloWorld {
  /* 这是我的第一个 Scala 程序
   * 以下程序将输出'Hello World!'
   */
  def main(args: Array[String]) {
    println("Hello, world!") // 输出 Hello World

    var myVar = 10;
    val myVar2 = "Hello, Scala";
    val myVar3 = true;
    println(myVar, myVar2, myVar3);

    var c = 0;
    var a = 60;
    c = a << 2;
    var d = a >> 2;
    val d2 = a >>> 2
    println(c, d, d2);
  }
}