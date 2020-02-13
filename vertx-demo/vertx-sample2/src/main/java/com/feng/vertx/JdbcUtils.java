package com.feng.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;

/**
 * JDBC操作工具类
 */
public class JdbcUtils {

    //用于操作数据库的客户端
    private JDBCClient dbClient;

    public JdbcUtils(Vertx vertx) {
        // 构造数据库的连接信息
        JsonObject dbConfig = new JsonObject();
        dbConfig.put("url", "jdbc:mysql://192.168.99.100:3307/test");
        dbConfig.put("driver_class", "com.mysql.jdbc.Driver");
        dbConfig.put("user", "root");
        dbConfig.put("password", "123456");

        // 创建客户端
        dbClient = JDBCClient.createShared(vertx, dbConfig);
    }

    /**
     * 提供一个公共方法来获取客户端
     * @return
     */
    public JDBCClient getDbClient() {
        return dbClient;
    }
}
