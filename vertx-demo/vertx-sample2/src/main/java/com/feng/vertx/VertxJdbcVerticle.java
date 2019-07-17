package com.feng.vertx;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.ResultSet;

import java.util.List;


/**
 * 访问数据库
 */
public class VertxJdbcVerticle extends AbstractVerticle {

    public static void main(String... args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new VertxJdbcVerticle());
    }

    @Override
    public void start() throws Exception {
        // 获取到数据库连接的客户端
        JDBCClient jdbcClient = new JdbcUtils(vertx).getDbClient();
        String SQL = "select * from test where id < ?";
        // 构造参数
        JsonArray params = new JsonArray().add(10);
        // 执行查询
        jdbcClient.queryWithParams(SQL, params, qryRes -> {
            if(qryRes.succeeded()) {
                // 获取到查询结果， Vert.x 对ResultSet进行了封装
                ResultSet resultSet = qryRes.result();
                // 把ResultSet转换为List<JsonObject>形式
                List<JsonObject> rows = resultSet.getRows();
                // 输出结果
                System.err.println(rows);
            }
            else {
                System.err.println("查询数据库异常");
            }
        });

        /*jdbcClient.queryStreamWithParams(SQL, params, obj -> {
            obj.map(row -> new JsonObject().put("id", row.column("id")).put("name", row.column("name")));
        });*/
    }
}
