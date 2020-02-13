package com.feng.springboot.activiti7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class
})
public class Springboot2Activiti7Application {

	public static void main(String[] args) {
		SpringApplication.run(Springboot2Activiti7Application.class, args);
	}

}
