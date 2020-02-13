package com.feng.scala.db

import java.sql.{Connection, DriverManager}

/**
  * 数据库操作工具类
  */
object DBUtils {

  val ip = "localhost"
  val port = "3306"
  val dbType = "mysql";
  val dbName = "test"
  val username = "root"
  val password = "123456"
  val url = "jdbc:" + dbType + "://" + ip + ":" + port + "/" + dbName + "?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true"

  // 使用MySQL8
  classOf[com.mysql.cj.jdbc.Driver]

  // 使用MySQL5
//  classOf[com.mysql.jdbc.Driver]

  /**
    * 获得连接通道
    * @return
    */
  def getConnection(): Connection = {
    DriverManager.getConnection(url, username, password)
  }

  /**
    * 关闭连接
    * @param conn
    */
  def close(conn: Connection): Unit = {
    try {
      if(conn != null || !conn.isClosed()) {
        conn.close()
      }
    }
    catch {
      case ex: Exception => {
        ex.printStackTrace()
      }
    }
  }

}
