package scala2

/**
  * 定义样例类(用于模式匹配)
  * @param name
  * @param age
  */
case class Person(name: String, age: Int) {

  var userName: String = name
  var userAge: Int = age

  def getUserName(): String = userName

  def getUserAge(): Int = age
}
