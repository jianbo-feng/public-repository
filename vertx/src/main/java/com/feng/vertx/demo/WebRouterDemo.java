package com.feng.vertx.demo;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

/**
 * 请求接口的同步、 异步处理请求
 */
public class WebRouterDemo {

    public static void main(String [] args){

        Vertx vertx = Vertx.vertx();

        //Web路由器
        Router router = Router.router(vertx);
        router.get("/sync").handler(context -> {
            //同步处理请求
            context.response().end("hello get!");
            System.err.println("sync: " + Thread.currentThread());
        });
        router.get("/async").blockingHandler(context -> {
            //异步处理请求
            //执行耗时操作
            //数据库访问
            //服务访问
            context.response().end("hello *!");
            System.err.println("async: " + Thread.currentThread());
        });

        HttpServer httpServer = vertx.createHttpServer();
        //处理request
        httpServer.requestHandler(router::accept);
        httpServer.listen(8080);

    }
}
