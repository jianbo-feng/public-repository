package com.feng.vertx.bus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;

public class FeedExample extends AbstractVerticle {

    private final static String VERTX_BLOG_ATOM = "http://vertx.io/feed.xml";

    public static void main(String... args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new FeedExample());
    }

    @Override
    public void start() throws Exception {
        // 宣布
        vertx.eventBus().consumer("announce", message -> {
            System.err.println("ANNOUNCE => " + message.body());
        });

        vertx.eventBus().consumer("errors", message -> {
            System.err.println("ERROR => " + message.body());
        });

        SimpleRegistry registry = new SimpleRegistry();
        registry.put("filterService", new ReleasePostFilter());
        CamelContext camelContext = new DefaultCamelContext(registry);
        camelContext.addRoutes(createMyRoutes());
        camelContext.start();
        CamelBridge.create(vertx, new CamelBridgeOptions(camelContext)
                .addInboundMapping(fromCamel("seda:announce").toVertx("announce"))
                .addInboundMapping(fromCamel("seda:errors").toVertx("errors")))
                .start();
    }

    private RouteBuilder createMyRoutes() throws Exception {
        return new RouteBuilder() {
            public void configure() throws Exception {
                errorHandler(deadLetterChannel("seda:errors"));
                // We pool the atom feeds from the source for further processing in the seda queue
                // we set the delay to 1 second for each pool.
                // Using splitEntries=true will during polling only fetch one RSS Entry at any given time.
                from("rss:" + VERTX_BLOG_ATOM +
                        "?splitEntries=true&consumer.delay=100").to("seda:feeds");

                from("seda:feeds")
                        // Filter
                        .filter().method("filterService", "isRelease")
                        // Transform (extract)
                        .transform(simple("${body.entries[0].title}"))
                        // Output
                        .to("seda:announce");
            }
        };
    }

}
