package com.feng.scala.file

import scala.io.{Codec, Source}

/**
  * 文件读取等等
  */
object SourceUse {

  def main(args: Array[String]): Unit = {
    val path = "C:\\work\\test\\num.txt"
    val file = Source.fromFile(path, Codec.UTF8.name)
    val content: StringBuilder = new StringBuilder()
    for (line <- file.getLines()) {
      println(line)
      content.append(line)
    }
    file.close()
    println("文件内容为：" + content.toString() + "\n")

    // 读取网络资源
   // val uri: String = "http://spark.apache.org"
    val uri = "https://www.baidu.com"
    var webResource = Source.fromURL(uri, Codec.UTF8.name)
    webResource.foreach(print)
    webResource.close()
    println("\n另外一种读取并循环输出的方法\n")
    webResource = Source.fromURL("http://spark.apache.org", Codec.UTF8.name);
//    webResource.getLines().foreach(println)
//    webResource.getLines().foreach(line => println(line))
    webResource.getLines().foreach(line => {
      print(line)
    })
    webResource.close()

    // 说明：val声明的变量不可变（是指句柄不可变，但是变量内部的值可以变或状态可变），但var可变

    // 读取项目中文件
    val resource = Source.getClass.getClassLoader.getResource("");
    println(resource)
  }
}
