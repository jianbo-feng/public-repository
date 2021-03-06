package com.feng.cratedemo.test;

import io.crate.client.jdbc.CrateDriver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Demo {

    public static final String URL = "jdbc:crate://localhost:5432/";

    public static final String USER = "crate";

    public static final String PASSWORD = "";

    public static void main(String... args) throws SQLException {
        Connection connection = DriverManager.getConnection( URL, USER, PASSWORD);
        if(!connection.isClosed()) {
            System.err.println("连接成功!!!!");
        }
        else {
            System.err.println("连接关闭!!!");
        }

        Statement statement = connection.createStatement();
        String sql = "SHOW TABLES";
        ResultSet resultSet = statement.executeQuery(sql);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int count = resultSetMetaData.getColumnCount();
        List<String> colNames = new ArrayList<>();
        for(int i=1; i <= count; i++){
            colNames.add(resultSetMetaData.getColumnName(i));//把列名存入向量colNames中
        }
        System.err.println("colNames => " + colNames);
        while (resultSet.next()) {
            for(String colName : colNames) {
                String colVal = resultSet.getString(colName);
                System.err.println("colName : '" + colName + "', value=> " + colVal);
            }
            String tableName = resultSet.getString("table_name");
            System.err.println("tableName => " + tableName);
        }

        sql = "SELECT id,name,age FROM demoapp LIMIT 100;";
        resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int colId = resultSet.getInt(1);
            String colName = resultSet.getString(2);
            String colAge = resultSet.getString(3);
            System.err.println("id：" + colId + ", name：" + colName + ", age：" + colAge);
        }
        statement.close();
        resultSet.close();
        connection.close();

        // 使用CrateDriver
        System.err.println("----------------------");
        test();
    }

    public static void test() {
        try {
            CrateDriver driver = new CrateDriver();
            Properties info = new Properties();
            info.setProperty("user", USER);
            info.setProperty("password", PASSWORD);
            Connection connection = driver.connect(URL, info);
            System.err.println("使用CreateDriver链接成功.....");
            connection.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
