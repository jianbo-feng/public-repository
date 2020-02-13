package com.feng.activiti.designer;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, org.activiti.spring.boot.SecurityAutoConfiguration.class})
public class ActivitiDesignerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivitiDesignerApplication.class, args);
    }

}
