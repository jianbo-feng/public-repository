package com.xh.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 扩展 Spring 的 AbstractRoutingDataSource 抽象类，重写 determineCurrentLookupKey 方法
 * 动态数据源
 * determineCurrentLookupKey() 方法决定使用哪个数据源
 *
 * @author xiaohe
 * @version V1.0.0
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * ThreadLocal 用于提供线程局部变量，在多线程环境可以保证各个线程里的变量独立于其它线程里的变量。
     * 也就是说 ThreadLocal 可以为每个线程创建一个【单独的变量副本】，相当于线程的 private static 类型变量。
     */
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     * 决定使用哪个数据源之前需要把多个数据源的信息以及默认数据源信息配置好
     *
     * @param defaultTargetDataSource 默认数据源
     * @param targetDataSources       目标数据源
     */
    public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }


    /**
     * 动态更新自定义数据源
     * @param defaultTargetDataSource 默认数据源
     * @param customDataSources 客户自定义数据源组
     */
    public void updateTargetDataSource(DataSource defaultTargetDataSource, Map<Object, Object> customDataSources) {
        if (null == defaultTargetDataSource) { // 从缓存中获取默认数据源
            String defaultDataSourceKey = DataSourceNames.FIRST;
            if (DynamicDataSourceConfig.TARGET_DATA_SOURCES.containsKey(defaultDataSourceKey)) {
                defaultTargetDataSource = (DataSource)DynamicDataSourceConfig.TARGET_DATA_SOURCES.get(defaultDataSourceKey);
            }
        }
        if (null != defaultTargetDataSource) {
            super.setDefaultTargetDataSource(defaultTargetDataSource);
        }
        super.setTargetDataSources(customDataSources);
        super.afterPropertiesSet();
    }



    @Override
    protected Object determineCurrentLookupKey() {
        return getDataSource();
    }

    public static void setDataSource(String dataSource) {
        CONTEXT_HOLDER.set(dataSource);
    }

    public static String getDataSource() {
        return CONTEXT_HOLDER.get();
    }

    public static void clearDataSource() {
        CONTEXT_HOLDER.remove();
    }

}
