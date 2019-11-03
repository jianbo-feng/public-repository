package com.feng.elasticsearch.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

/**
 * 测试ES-JDBC（使用opendistro）
 * @see <a href="https://github.com/opendistro-for-elasticsearch/sql-jdbc">参考</a>
 */
public class TestEsJdbc {

    public static void main(String... args) throws Exception {

        String url = "jdbc:elasticsearch://localhost:9200";
        Properties properties = new Properties();
        properties.put("useSSL", "true");
        String user = "", password = "";
        properties.put("user", user);
        properties.put("password", password);
//        Connection conn = DriverManager.getConnection(url, properties);
        Connection conn = DriverManager.getConnection(url);
        Statement stat = conn.createStatement();

        System.err.println("ES-JDBC链接成功");
        stat.close();
        conn.close();
    }
}
