package scala2

/**
  * Scala Trait(特征) 相当于 Java 的接口，实际上它比接口还功能强大。
  *
  * 与接口不同的是，它还可以定义属性和方法的实现。
  *
  * 一般情况下Scala的类只能够继承单一父类，但是如果是 Trait(特征) 的话就可以继承多个，从结果来看就是实现了多重继承。
  *
  * Trait(特征) 定义的方式与类类似，但它使用的关键字是 trait
  */
trait Equal {

  def isEqual(x: Any): Boolean

  def isNotEqual(x: Any): Boolean

}
