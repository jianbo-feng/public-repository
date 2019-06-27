package com.feng.cratedemo.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Demo {

    public static void main(String... args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:crate://localhost:5432/", "crate", "");
        if(!connection.isClosed()) {
            System.err.println("连接成功!!!!");
        }
        else {
            System.err.println("连接关闭");
        }
        connection.close();
    }
}
