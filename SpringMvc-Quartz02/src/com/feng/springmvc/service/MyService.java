package com.feng.springmvc.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import freemarker.template.Configuration;

@Service
public class MyService {
	
	@Autowired
    private JavaMailSender mailSender;
	
	@Autowired
	private Configuration freemarkerConfig;
	
	public void test() {
		System.err.println("MyService.test =>" + DateUtils.formatDate(new Date()) + 
				"\n\t" + freemarkerConfig 
				+ "\n\t" + mailSender + "\n");
		
		try {
			Map<String, Object> ret = sendSimpleMail();
			System.err.println("发送邮件结果：\n\t" + ret);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
     * 发送简单邮件
     * @return
     */
    public Map<String, Object> sendSimpleMail() {
        Map<String, Object> ret = new HashMap<>();
        try {
        	System.err.println("开始发送简单邮件....");
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("445121408@qq.com");
            message.setTo("405727062@qq.com");
            message.setSubject("主题：简单邮件");
            message.setText("测试邮件内容");
            mailSender.send(message);
            ret.put("code", 200);
            System.err.println("发送简单邮件结束");
        }
        catch (Exception e) {
            e.printStackTrace();
            ret.put("code", 500);
            ret.put("error", e.getMessage());
        }
        return ret;
    }

}
