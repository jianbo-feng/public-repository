package com.xh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author xiaohe
 */
@MapperScan("com.xh.mapper")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@Import({DynamicDataSourceConfig.class, DataConfig2.class})
public class SpringBootDynamicDataSourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDynamicDataSourceApplication.class, args);
    }

}
