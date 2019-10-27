package com.feng.datasource.multiple.dynamic.base;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @see <a href="https://yq.aliyun.com/articles/620075/">参考</>
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceType();
    }

}
