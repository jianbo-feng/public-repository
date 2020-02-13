package com.feng.scala.db

import java.sql.{PreparedStatement, ResultSet, ResultSetMetaData}

import com.feng.scala.entity.User

import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer}

/**
  * 数据库操作
  */
class Operations {

  //insert
  /**
    * 插入用户信息
    * @param user
    * @return
    */
  def addUser(user: User): Boolean = {
    val conn = DBUtils.getConnection()
    try {
      val sql = new StringBuffer()
        .append("insert into t_user(userId, userName, password, phone) values(?, ?, ?, ?)")
      val pstm = conn.prepareStatement(sql.toString())
      pstm.setInt(1, user.userId)
      pstm.setString(2, user.userName)
      pstm.setString(3, user.password)
      pstm.setString(4, user.phone)
      pstm.executeUpdate() > 0
    }
    finally {
      conn.close()
    }
  }

  /**
    * 删除用户信息
    * @param userId
    * @return
    */
  def delete(userId: Int): Boolean = {
    val conn = DBUtils.getConnection()
    try {
      val sql = "DELETE FROM t_user WHERE userId = ?"
      val ps = conn.prepareStatement(sql)
      ps.setObject(1, userId)
      ps.executeUpdate() > 0
    }
    finally {
      conn.close()
    }
  }

  /**
    * 更新用户信息
    * @param user
    * @return
    */
  def update(user: User): Boolean = {
    val conn = DBUtils.getConnection()
    try {
      val sql = "UPDATE t_user SET userName = ?, phone = ? WHERE = userId = ?"
      val ps = conn.prepareStatement(sql)
      ps.setObject(1, user.userName)
      ps.setObject(2, user.phone)

      ps.executeUpdate() > 0
    }
    finally {
      conn.close()
    }
  }

  def query2(userId: Int): ArrayBuffer[mutable.HashMap[String, Any]] = {
    val conn = DBUtils.getConnection()
    try {
      val sql = new mutable.StringBuilder()
        .append("SELECT userId, userName, password, phone ")
        .append("FROM t_user ")
        .append("WHERE userId = ?")
      val ps = conn.prepareStatement(sql.toString())
      ps.setInt(1, userId)
      val rs = ps.executeQuery()
      val rsmd = rs.getMetaData()
      val size = rsmd.getColumnCount()
      val buffer = new ArrayBuffer[mutable.HashMap[String, Any]]()
      while (rs.next()) {
        val map = mutable.HashMap[String, Any]()
        for (i <- 1 to size) {
          map += (rsmd.getColumnLabel(i) -> rs.getString(i))
        }
        buffer += map
      }
      buffer
    }
    finally  {
      conn.close()
    }
  }

  def query(userId: Int): ListBuffer[mutable.HashMap[String, Any]] = {
    val conn = DBUtils.getConnection()
    try {
      var isSetUserId: Boolean = false
      val sql: mutable.StringBuilder = new mutable.StringBuilder("SELECT userId, userName, password, phone FROM t_user WHERE 1 = 1 ")
      if (userId > 0 ) {
        sql.append(" AND useId = ?")
        isSetUserId = true
      }

      val ps: PreparedStatement = conn.prepareStatement(sql.toString())
      if (isSetUserId) {
        ps.setInt(1, userId)
      }
      val rs: ResultSet = ps.executeQuery()
      val rsmd: ResultSetMetaData = rs.getMetaData()
      val size: Int = rsmd.getColumnCount()
//      val array: mutable.ListBuffer[mutable.HashMap[String, Any]]()
      val array = new ListBuffer[mutable.HashMap[String, Any]]()
      while (rs.next()) {
        val map = mutable.HashMap[String, Any]()
        for (i <- 1 to size) {
          map += (rsmd.getColumnLabel(i) -> rs.getString(i))

        }
        array += map
        println(map)
      }
      println("array => ", array)
      // 返回列表
      array
    }
    catch  {
      case e: Exception => e.printStackTrace()
        // 出现异常，则返回null
        null
    }
    finally {
      conn.close()
    }
  }

}
