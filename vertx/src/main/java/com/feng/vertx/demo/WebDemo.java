package com.feng.vertx.demo;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

/**
 * Vertx-Web测试
 */
public class WebDemo {

    public static void main(String [] args){


        Vertx vertx = Vertx.vertx();

        //web路由器
        Router router = Router.router(vertx);

        //接口地址为“/”
        router.route("/").handler(context -> {
            context.request();
            context.response().end("hello root!");
        });

        //接口地址为 “/abc”
        router.route("/abc").handler((RoutingContext context) ->{
            HttpServerRequest request = context.request();
            String name = request.getParam("name");
            String type = request.getParam("type");
            String itheima = request.getHeader("itheima");

            context.response().end("hello abc!"+ name+ "; type = "+ type +" ; " + itheima);
        });


        HttpServer httpServer = vertx.createHttpServer();
        //处理request（监听请求的接口地址）
        httpServer.requestHandler(router::accept);
        //监听请求的端口
        httpServer.listen(8080);
    }
}
