package com.feng.scala.flink.test

import org.apache.flink.api.scala._
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.table.api.scala._
import org.apache.flink.table.api.{Table, TableEnvironment}

/**
  * 查询测试
  */
object SQLTest {

  case class Person(name: String, score: String)

  def main(args: Array[String]) : Unit = {

    // 获取执行环境
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    // 获取Table
    val tableEnv = TableEnvironment.getTableEnvironment(env)

    // 读取数据源
    val source1 = env.readTextFile("C:/work/test/person.txt")


    val source2: DataStream[Person] = source1.map(x => {
      println("获取数据：" + x)
      val strs = x.split(" ")
      (Person (strs(0), strs(1))) // 返回结果
    })

    // 将DataStream转换成Table
    val table1 = tableEnv.fromDataStream(source2)
    // 注册表，表名为：person
    tableEnv.registerTable("person", table1)
    // 获取表中的所有信息
    var rs: Table = tableEnv.sqlQuery("select *  from person")
    val stream: DataStream[String] = rs
      // 过滤获取name这一列的数据
      .select("name")
      // 将表转成DataStream
      .toAppendStream[String]
    stream.print()
    println("===================== end =========================")
    env.execute("flinkSQL")
  }
}