package com.feng.datasource.multiple.dynamic.mybatis;

import com.feng.datasource.multiple.dynamic.mybatis.aop.DynamicDataSourceAnnotationAdvisor;
import com.feng.datasource.multiple.dynamic.mybatis.aop.DynamicDataSourceAnnotationInterceptor;
import com.feng.datasource.multiple.dynamic.mybatis.register.DynamicDataSourceRegister;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Import(DynamicDataSourceRegister.class)
@MapperScan("com.feng.datasource.multiple.dynamic.mybatis.repository")
@SpringBootApplication
@EnableTransactionManagement
public class MultipleDataSourceDynamicMybatisApplication {

	@Bean
	public DynamicDataSourceAnnotationAdvisor dynamicDatasourceAnnotationAdvisor() {
		return new DynamicDataSourceAnnotationAdvisor(new DynamicDataSourceAnnotationInterceptor());
	}

	public static void main(String[] args) {
		SpringApplication.run(MultipleDataSourceDynamicMybatisApplication.class, args);
	}

}
