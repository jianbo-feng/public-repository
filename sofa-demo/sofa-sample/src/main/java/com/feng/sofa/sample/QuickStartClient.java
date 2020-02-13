package com.feng.sofa.sample;

import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.feng.sofa.sample.service.HelloService;

/**
 * 启动客户端
 */
public class QuickStartClient {

    public static void main(String... args) {
        ConsumerConfig<HelloService> consumerConfig = new ConsumerConfig<HelloService>()
                .setInterfaceId(HelloService.class.getName())   // 指定接口
                .setProtocol("bolt")        // 指定协议
                .setDirectUrl("bolt://127.0.0.1:9696"); // 指定直连地址
        HelloService helloService = consumerConfig.refer();

        while (true) {
            System.err.println(helloService.sayHello("world"));
            try {
                Thread.sleep(2000);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
