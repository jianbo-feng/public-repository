package com.xh.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 配置多数据源
 * @author xiaohe
 * @version V1.0.0
 */
@Configuration
public class DynamicDataSourceConfig {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceConfig.class);


    @Autowired
    private Environment env;

    /**
     * 所有目标数据源
     */
    public static Map<Object, Object> TARGET_DATA_SOURCES = Maps.newHashMap();

    @Bean
    @ConfigurationProperties("spring.datasource.druid.first")
    public DataSource firstDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.second")
    public DataSource secondDataSource(){
        return DruidDataSourceBuilder.create().build();
    }



    @Bean
    @Primary
    public DynamicDataSource dataSource(DataSource firstDataSource, DataSource secondDataSource) {
        TARGET_DATA_SOURCES.put(DataSourceNames.FIRST, firstDataSource);
        TARGET_DATA_SOURCES.put(DataSourceNames.SECOND, secondDataSource);
        return new DynamicDataSource(firstDataSource, TARGET_DATA_SOURCES);
    }
}
