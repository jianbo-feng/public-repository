package com.zeshan.kafka;

import com.alibaba.fastjson.JSON;
import com.zeshan.kafka.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("kafka-producer")
public class ProducerController {

    @Autowired
    private  KafkaTemplate kafkaTemplate;

    @RequestMapping("send")
    @ResponseBody
    public String  sengMessage(HttpServletRequest request, HttpServletResponse response){
        String message = request.getParameter("message");
        try {
            kafkaTemplate.send("demo",message);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return  "error";
        }
    }


    /**
     * 发送JSON数据
     * @return
     */
    @RequestMapping("sendJson")
    @ResponseBody
    public String sendJson() {
        try {
            String topic = "topic.json";
            User user = new User();
            user.setUserId(UUID.randomUUID().toString());
            user.setUserName("张三");
            user.setUserPwd("12121212");
            user.setUserAge(55);
            Map<String, Object> content = new HashMap<>();
            content.put("topic", topic);
            content.put("user", user);
            content.put("time", System.currentTimeMillis());

            String json = JSON.toJSONString(content);
            System.err.println("json+++++++++++++++++++++  message = "+ json);

            ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, json);
            future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
                @Override
                public void onFailure(Throwable throwable) {
                    System.out.println("json message send failed: ");
                }

                @Override
                public void onSuccess(SendResult<String, Object> result) {
                    System.out.println("json message send OK. sendResult => " + result.toString());
                }
            });

            return "send json success";
        }
        catch (Exception e) {
            return "send json failure";
        }
    }
}
