package com.feng.vertx.demo;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;


/**
 * 对用户的请求解析时间（测试给定的请求量时vertx处理所消耗的时间）
 */

public class Demo5 {

    public static void main(String[] args) {

        Vertx vertx = Vertx.vertx();

        //web路由器
        Router router = Router.router(vertx);
        router.get("/vertx").blockingHandler(context -> {

            long start = System.currentTimeMillis();
            Pi.computePi(20000);
            long end = System.currentTimeMillis();

            //同步处理请求
            context.response().end("hello" + (end - start));

        });

        HttpServer httpServer = vertx.createHttpServer();
        httpServer.requestHandler(router::accept);
        httpServer.listen(8080);
    }
}
