package com.xh.service.impl;

import com.xh.MyException;
import com.xh.datasource.DynamicDataSource;
import com.xh.datasource.DynamicDataSourceConfig;
import com.xh.service.DataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class DataSourceServiceImpl implements DataSourceService {

    private static final String DATASOURCE_TYPE_DEFAULT = "com.zaxxer.hikari.HikariDataSource";

    @Autowired
    @Lazy
    private DynamicDataSource dynamicDataSource;

    @Override
    public void addDataSource(String dsName) throws MyException {
        dsName = dsName == null ? "" : dsName.trim();
        if (!"".equals(dsName)) {
            Map<String, Object> dsMap = new HashMap<>();
            dsMap.put("type", "com.alibaba.druid.pool.DruidDataSource");
            dsMap.put("driver", "com.mysql.cj.jdbc.Driver");
            dsMap.put("url", "jdbc:mysql://127.0.0.1:3306/test?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true");
            dsMap.put("username", "root");
            dsMap.put("password", "jianbo@feng.0825");
            DataSource ds = buildDataSource(dsMap);
            if (ds != null) {
                Map<Object, Object> dataSources = new HashMap<>(DynamicDataSourceConfig.TARGET_DATA_SOURCES);
                dataSources.put(dsName, ds);
                dynamicDataSource.updateTargetDataSource(null, dataSources);
                DynamicDataSourceConfig.TARGET_DATA_SOURCES.put(dsName, ds);

                // 添加数据库之后可以写入配置文件中，下一次启动的时候会自动读取

            }
            else {
                throw new MyException("构建数据源失败, 数据源名称=>" + dsName);
            }
        }
        else {
            throw new MyException("数据源名称为空");
        }
    }

    @Override
    public DataSource getDataSource(String dsName) {
        if (null != dsName && DynamicDataSourceConfig.TARGET_DATA_SOURCES.containsKey(dsName)) {
            return (DataSource) DynamicDataSourceConfig.TARGET_DATA_SOURCES.get(dsName);
        }
        return null;
    }

    public DataSource buildDataSource(Map<String, Object> dataSourceMap) {
        try {
            Object type = dataSourceMap.get("type");
            if (type == null) {
                type = DATASOURCE_TYPE_DEFAULT;// 默认DataSource
            }
            Class<? extends DataSource> dataSourceType;
            dataSourceType = (Class<? extends DataSource>) Class.forName((String) type);
            String driverClassName = dataSourceMap.get("driver").toString();
            String url = dataSourceMap.get("url").toString();
            String username = dataSourceMap.get("username").toString();
            String password = dataSourceMap.get("password").toString();
            // 自定义DataSource配置
            DataSourceBuilder factory = DataSourceBuilder.create().driverClassName(driverClassName).url(url)
                    .username(username).password(password).type(dataSourceType);
            return factory.build();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }
}
