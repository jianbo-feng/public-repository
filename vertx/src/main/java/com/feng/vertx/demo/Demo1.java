package com.feng.vertx.demo;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;

public class Demo1 {

    //用vertx实现http服务器
    public static void main(String[] args){

        //vertx核心
        Vertx vertx = Vertx.vertx();

        //创建http的server
        HttpServer httpServer = vertx.createHttpServer();
        //服务器响应
        httpServer.requestHandler(request -> {
            request.response().end("Hello Vertx!");
        });

        httpServer.listen(8080);
    }
}
