package com.feng.vertx.demo;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

/**
 * Vertx应用的 get 、post请求
 */
public class GetPostAction {

    public static void main(String [] args){

        Vertx vertx = Vertx.vertx();

        //Web路由器
        Router router = Router.router(vertx);
        //get请求
        router.get("/get").handler(context -> {
            context.response().end("hello get!");
        });
        //post请求
        router.post("/post").handler(context -> {
            context.response().end("hello post!");
        });

        HttpServer httpServer = vertx.createHttpServer();
        //处理request
        httpServer.requestHandler(router::accept);
        httpServer.listen(8080);
    }
}
