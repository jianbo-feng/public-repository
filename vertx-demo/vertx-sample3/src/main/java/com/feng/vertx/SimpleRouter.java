package com.feng.vertx;

import com.feng.vertx.common.RouterConfig;
import com.feng.vertx.common.ServerConfig;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

/**
 * 路由器
 */
public class SimpleRouter extends AbstractVerticle {


    @Override
    public void start() throws Exception {
        // 创建HttpServer
        HttpServer httpServer = vertx.createHttpServer();
        // 创建路由器对象
        Router router = Router.router(vertx);
        // 监听/index 地址
        router.route(RouterConfig.INDEX).handler(req -> {
            req.response().end("INDEX SUCCESS");
        });

        // 参数获取 http://localhost:8888/method?param=hello
        router.route(HttpMethod.GET, "/method").handler(routingContext -> {
            HttpServerRequest request2 = routingContext.request();
            String value2 = request2.getParam("param");
            System.err.println("获取'param'的值为：" + value2);
            routingContext.response()
                    .putHeader("Content-type", "text/html;charset=utf-8")
                    .end("接收到的参数值为：" + value2);
        });

        // POST方式提交数据
        router.route().handler(BodyHandler.create());
        router.route(RouterConfig.POST).handler(routingContext -> {
            String res = routingContext.getBodyAsString("UTF-8");
            System.err.println("POST 方式接收的数据：" + res);
            routingContext.response().end("POST END");

        });

        // 监听 /home 地址
        router.route(RouterConfig.HOME).handler(new HomeHandler());

        // 监听 http://localhost:8888/query/xiaoming/xm123
        /*router.route(HttpMethod.GET, "/query/:user/:pass").handler(context -> {
            HttpServerRequest request = context.request();
            String user = request.getParam("user");
            String pass = request.getParam("pass");

            context.response()
                    .putHeader("Content-type", "text/html;charset=utf-8")
                    .end("接收到的用户名为：" + user + " ,<br/>接收到的密码为：" + pass);
        });*/
        router.route(HttpMethod.GET, RouterConfig.QUERY_USER_INFO).handler(new QueryHandler());

        // 把请求交给路由处理
        httpServer.requestHandler(router);
        //httpServer.requestHandler(router::accept);  // 旧写法，已抛弃
        httpServer.listen(ServerConfig.PORT);
    }

    public static void main(String... args) {
        Vertx.vertx().deployVerticle(new SimpleRouter());
    }
}

/**
 * 访问链接：http://localhost:8888/index ， 访问结果：INDEX SUCCESS
 */