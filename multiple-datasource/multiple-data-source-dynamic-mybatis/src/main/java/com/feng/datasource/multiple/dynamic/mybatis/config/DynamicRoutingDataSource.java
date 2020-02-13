package com.feng.datasource.multiple.dynamic.mybatis.config;

import com.feng.datasource.multiple.dynamic.mybatis.register.DynamicDataSourceRegister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.*;

/**
 * @Auther: yukong
 * @Date: 2018/8/15 10:47
 * @Description: 动态数据源路由配置
 */
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    @Value("${spring.datasource.default}")
    private String defaultDataSourceName;


    private static Logger logger = LoggerFactory.getLogger(DynamicRoutingDataSource.class);

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSourceName = DynamicDataSourceContextHolder.getDataSourceRouterKey();
        logger.info("当前数据源是：{}", dataSourceName);
        return DynamicDataSourceContextHolder.getDataSourceRouterKey();
    }

    /**
     * 动态更新自定义数据源
     * @param defaultTargetDataSource 默认数据源
     * @param customDataSources 客户自定义数据源组
     */
    public void updateTargetDataSource(DataSource defaultTargetDataSource, Map<Object, Object> customDataSources) {
        if (null == defaultTargetDataSource) { // 从缓存中获取默认数据源
            String defaultDataSourceKey = defaultDataSourceName;
            if (DynamicDataSourceRegister.customDataSources.containsKey(defaultDataSourceKey)) {
                defaultTargetDataSource = (DataSource)DynamicDataSourceRegister.customDataSources.get(defaultDataSourceKey);
            }
        }
        if (null != defaultTargetDataSource) {
            super.setDefaultTargetDataSource(defaultTargetDataSource);
        }
        super.setTargetDataSources(customDataSources);
        super.afterPropertiesSet();
    }
}
