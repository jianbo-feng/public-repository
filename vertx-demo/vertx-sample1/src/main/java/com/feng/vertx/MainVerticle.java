package com.feng.vertx;

import io.vertx.core.AbstractVerticle;

public class MainVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        vertx.deployVerticle(MyFirstVerticle.class.getName());
    }
}
