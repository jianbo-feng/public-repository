package com.feng.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.SQLConnection;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库链接以及事务操作
 */
public class GetConnection extends AbstractVerticle {



    public static void main(String... args) {
        Vertx.vertx().deployVerticle(new GetConnection());
    }

    @Override
    public void start() throws Exception {
        JDBCClient jdbcClient = new JdbcUtils(vertx).getDbClient();
        System.err.println("获取数据库客户端");


        List<JsonObject> rows1 = new ArrayList<>();
        // 获取数据库连接
        jdbcClient.getConnection(con -> {
            if (con.succeeded()) {
                System.err.println("获取到数据库连接");

                // 获取到的连接对象
                SQLConnection connection = con.result();


                // 执行查询操作
                connection.query("select * from test", rs -> {
                    // 处理查询结果
                    if (rs.succeeded()) {
//                        rows1 = rs.result().getRows();
                        rs.result().getRows().forEach(c -> {
                            rows1.add(c);
                        });
                        System.err.println(rows1);
                    }
                    else {
                        System.err.println("查询处理失败");
                    }
                });

                // 开启事务
                connection.setAutoCommit(false, (v) -> {
                   if (v.succeeded()) {
                       // 事务开启成功 执行crud操作
                       connection.update("update test set name = '2222-bk' where name = '2222'", up -> {

                           if (up.succeeded()) {
                               // 再来一笔写操作
                               connection.update("insert into test values (3,'33333') ", up2 -> {
                                   if (up2.succeeded()) {
                                       // 提交事务
                                       connection.commit(rx -> {
                                           if (rx.succeeded()) {
                                               // 事务提交成功
                                           }
                                       });
                                   } else {
                                       connection.rollback(rb -> {
                                           if (rb.succeeded()) {
                                               // 事务回滚成功
                                           }
                                       });
                                   }
                               });
                           } else {
                               connection.rollback(rb -> {
                                   if (rb.succeeded()) {
                                       // 事务回滚成功
                                   }
                               });
                           }
                       });
                   }
                   else {
                       System.err.println("开启事务失败");
                   }
                });
            }
            else {
                System.err.println("获取数据库连接失败");
            }
        });
    }
}
