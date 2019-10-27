package com.feng.datasource.multiple.dynamic.mybatis.properties;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class PropertiesTest {

    public static void read() {
        Properties prop = new Properties();
        try {
            //这个getResourceAsStream方法就是把文件转为inputStream的方式
            prop.load(PropertiesTest.class.getResourceAsStream("/config/test.properties"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        String cookie = prop.getProperty("cookie");
        System.err.println("cookie = " + cookie);
    }

    public static void write() {
        try {
            Properties prop = new Properties();
            //这儿是获取文件的绝对路径的。
            FileOutputStream fileOutputStream = new FileOutputStream(PropertiesTest.class.getResource("/config/test.properties").getFile());
            prop.setProperty("cookie", "123456");
            //store方法，第一个参数是输入流，第二个是提示信息。#Sun Jan 07 18:47:58 CST 2018
//            prop.store(fileOutputStream, new Date().toString());
            prop.store(fileOutputStream, "Cookie-Setting");
            fileOutputStream.flush();
            fileOutputStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String... args) {
//        write();
        read();
    }
}
