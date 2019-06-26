package com.feng.hadoop.hive.demo;

import org.apache.hive.jdbc.HiveDataSource;
import org.springframework.data.hadoop.hive.HiveClientFactory;
import org.springframework.data.hadoop.hive.HiveClientFactoryBean;
import org.springframework.data.hadoop.hive.HiveTemplate;
import org.springframework.jdbc.datasource.AbstractDataSource;

import javax.sql.DataSource;
import java.util.List;

public class Test {

    public static void main(String... args) {

        try {
            DataSource dataSource = new HiveDataSource();
            HiveClientFactoryBean hiveClientFactoryBean = new HiveClientFactoryBean();
            hiveClientFactoryBean.setHiveDataSource(dataSource);
            hiveClientFactoryBean.afterPropertiesSet();
            HiveClientFactory factory = hiveClientFactoryBean.getObject();
            HiveTemplate hiveTemplate = new HiveTemplate(factory);
            List<String> query = hiveTemplate.query("select name from tablename limit 0,10");
            query.forEach(s -> System.err.println(s));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
