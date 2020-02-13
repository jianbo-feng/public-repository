package com.feng.datasource.multiple.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//@MapperScan("com.feng.datasource.multiple.mybatis.repository")
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class MultipleDataSourceMybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultipleDataSourceMybatisApplication.class, args);
	}

}
