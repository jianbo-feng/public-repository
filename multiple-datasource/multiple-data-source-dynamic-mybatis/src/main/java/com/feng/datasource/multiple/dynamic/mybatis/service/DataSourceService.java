package com.feng.datasource.multiple.dynamic.mybatis.service;


import com.feng.datasource.multiple.dynamic.mybatis.exception.MyException;

import javax.sql.DataSource;

/**
 * 数据源操作
 */
public interface DataSourceService {

    /**
     * 新增数据源
     * @param dsName 数据源名称
     */
    void addDataSource(String dsName) throws MyException;

    /**
     * 获取数据源
     * @param dsName 数据源名称
     * @return
     */
    DataSource getDataSource(String dsName);
}
