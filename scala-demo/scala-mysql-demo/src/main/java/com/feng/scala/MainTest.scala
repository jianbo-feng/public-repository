package com.feng.scala

import com.feng.scala.db.Operations
import com.feng.scala.entity.User

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
  * MySQL连接以及操作测试
  */
object MainTest {

  def main(args: Array[String]): Unit = {
    val op = new Operations()
    /*val user = new User(111, "张三1212", "123456", "012012012")
    val isSave = op.addUser(user)
    println("是否添加成功：" + isSave)*/

    val list: ListBuffer[mutable.HashMap[String, Any]] = op.query(0)
    for(entry <- list) {
      println("entry => ", entry)
    }

    println("查询方式2\n")
    val ret1 = op.query2(111)
    println(ret1)
  }

}
