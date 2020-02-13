package com.feng.vertx;

import com.alibaba.fastjson.JSON;
import com.feng.vertx.bean.User;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;

import java.util.UUID;

/**
 * 查询处理器
 */
public class QueryHandler implements Handler<RoutingContext> {

    @Override
    public void handle(RoutingContext event) {
        HttpServerRequest request = event.request();
        String user = request.getParam("user");
        String pass = request.getParam("pass");

        String received = "接收到的用户名为：" + user + " ,<br/>接收到的密码为：" + pass;

        String json = JSON.toJSONString(new User(UUID.randomUUID().toString(), user, pass));
        event.response()
                .putHeader("Content-type", "text/html;charset=utf-8")
                .end(json);
    }
}

/**
 * 访问地址：http://localhost:8888/query/feng/1212
 * 显示结果：{"userId":"a8fd420b-775b-4521-a49d-4776308c5ad1","userName":"feng","userPass":"1212"}
 */
