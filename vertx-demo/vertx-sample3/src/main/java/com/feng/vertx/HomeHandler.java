package com.feng.vertx;

import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.RoutingContext;

/**
 * HOME 界面监听器
 */
public class HomeHandler implements Handler<RoutingContext> {

    @Override
    public void handle(RoutingContext event) {
        event.response()
                .putHeader("Content-type", "text/html;charset=utf-8")
//                .write(Buffer.buffer("HomeController")).end();
                .end("HomeController");
    }
}
