package com.feng.hadoop.hive;


import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@ConfigurationProperties(prefix = "hive")
public class HiveDruidConfig {

    @Value("${hive.url}")
    private String url;

    @Value("${hive.user}")
    private String user;

    @Value("${hive.password}")
    private String password;

    @Value("${hive.driver-class-name}")
    private String driverClassName;

    @Value("${hive.initial-size}")
    private int initialSize;

    @Value("${hive.min-idle}")
    private int minIdle;

    @Value("${hive.max-active}")
    private int maxActive;

    @Value("${hive.max-wait}")
    private int maxWait;

    @Value("${hive.time-between-eviction-runs-millis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${hive.min-evictable-idle-time-millis}")
    private int minEvictableIdleTimeMillis;

    @Value("${hive.validation-query}")
    private String validationQuery;

    @Value("${hive.test-while-idle}")
    private boolean testWhileIdle;

    @Value("${hive.test-on-borrow}")
    private boolean testOnBorrow;

    @Value("${hive.test-on-return}")
    private boolean testOnReturn;

    @Value("${hive.pool-prepared-statements}")
    private boolean poolPreparedStatements;

    @Value("${hive.max-pool-prepared-statement-per-connection-size}")
    private int maxPoolPreparedStatementPerConnectionSize;

    @Bean(name = "hiveDruidDataSource")
    @Qualifier("hiveDruidDataSource")
    public DataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(url);
        datasource.setUsername(user);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);

        // pool configuration
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        return datasource;
    }

    // 此处省略各个属性的get和set方法

    @Bean(name = "hiveDruidTemplate")
    public JdbcTemplate hiveDruidTemplate(@Qualifier("hiveDruidDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
