package com.feng.datasource.multiple.dynamic.base;

import java.util.ArrayList;
import java.util.List;

public class DynamicDataSourceContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static List<String> dataSourceIds = new ArrayList<>();

    public static void setDataSourceType(String dataSourceType) {
        contextHolder.set(dataSourceType);
    }



    public static String getDataSourceType() {
        return contextHolder.get();
    }



    public static void clearDataSourceType() {
        contextHolder.remove();
    }



    /**

     * 判断指定DataSrouce当前是否存在

     *

     * @param dataSourceId

     * @author zhoukai7

     * @create 7/27/18

     */

    public static boolean containsDataSource(String dataSourceId){
        return dataSourceIds.contains(dataSourceId);

    }
}
