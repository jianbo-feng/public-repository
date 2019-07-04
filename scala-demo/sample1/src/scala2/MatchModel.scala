package scala2

/**
  * 模式匹配
  */
object MatchModel {

  def main(args : Array[String]): Unit = {
    println(matchTest(3))

    val alice = new Person("Alice", 25)
    val bob = new Person("Bob", 24)
    val charlie = new Person("Charlie", 32)


    // 使用此种类的模式匹配时，需要定义样例类，如
    // case class Person(name: String, age: Int)
    for (person <- List(alice, bob, charlie)) {
      person match {
        case Person("Alice", 25) => println("Hi Alice!")
        case Person("Bob", 32) => println("Hi Bob!")
        case Person(name, age) =>
        println("Age: " + age + " year, name: " + name + "?")
      }
    }
  }

  /**
    * 模式匹配：类似Java的Switch-Case
    * @param x
    * @return
    */
  def matchTest(x: Int): String = x match {
    case 1 => "one"
    case 2 => "two"
    case _ => "many"
  }
}

/**
  * 控制台打印结果
  * many
  * Hi Alice!
  * Age: 24 year, name: Bob?
  * Age: 32 year, name: Charlie?
  */
