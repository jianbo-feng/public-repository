package com.xh.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.xh.config.TestBean;
import com.xh.entity.DbColumn;
import com.xh.service.DataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private DataSourceService sourceService;

    @Autowired
    private TestBean bean2;

    /**
     * 数据源信息
     */
    @RequestMapping("ds")
    public Map<String, Object> ds() {
        Map<String, Object> data = new HashMap<>();
        data.put("code", 200);
        try {
            System.err.println("testBean => " + bean2);
            String dsName = "testDs";
            sourceService.addDataSource(dsName);
            // 获取默认节点数据库
//            DataSource ds = sourceService.getDataSource(DataSourceNames.FIRST);
            DataSource ds = sourceService.getDataSource(dsName);
            if (ds == null) {
                data.put("ds", "is null");
            }
            else {
                data.put("ds", "isnot null");
                Connection connection = ds.getConnection();

                DatabaseMetaData databaseMetaData = connection.getMetaData();
                System.err.println("databaseMetaData => " + databaseMetaData);
                System.err.println("databaseName: " + databaseMetaData.getUserName()
                        + ", DriverName: " + databaseMetaData.getDriverName()
                        + ", UserName: " + databaseMetaData.getUserName()
                        + ", DriverVersion: " + databaseMetaData.getDriverVersion()
                        + ", DatabaseProductName: " + databaseMetaData.getDatabaseProductName()
                        + ", DatabaseProductVersion: " + databaseMetaData.getDatabaseProductVersion()
                        + ", URL: " + databaseMetaData.getURL()
                        + ", DatabaseMajorVersion: " + databaseMetaData.getDatabaseMajorVersion()
                        + ", DatabaseMinorVersion: " + databaseMetaData.getDatabaseMinorVersion()
                );

                String sql = "select userId \"userId\", userName \"userName\",`password` \"password\", phone from test.t_user";
                PreparedStatement pstmt = connection.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
                ResultSetMetaData metaData = rs.getMetaData();
                String metaDataJson = JSON.toJSONString(metaData);
                // {"columnCount":4,"fields":[{"autoIncrement":false,"binary":false,"blob":false,"collationIndex":63,"columnLabel":"userId","databaseName":"test","decimals":0,"encoding":"ISO-8859-1","flags":20483,"fromFunction":false,"fullName":"t_user.userId","javaType":4,"length":11,"multipleKey":false,"mysqlType":"INT","mysqlTypeId":3,"name":"userId","notNull":true,"originalName":"userId","originalTableName":"t_user","primaryKey":true,"readOnly":false,"singleBit":false,"tableName":"t_user","uniqueKey":false,"unsigned":false,"valueNeedsQuoting":false,"zeroFill":false},{"autoIncrement":false,"binary":false,"blob":false,"collationIndex":255,"columnLabel":"userName","databaseName":"test","decimals":0,"encoding":"UTF-8","flags":0,"fromFunction":false,"fullName":"t_user.userName","javaType":12,"length":1020,"multipleKey":false,"mysqlType":"VARCHAR","mysqlTypeId":253,"name":"userName","notNull":false,"originalName":"userName","originalTableName":"t_user","primaryKey":false,"readOnly":false,"singleBit":false,"tableName":"t_user","uniqueKey":false,"unsigned":false,"valueNeedsQuoting":true,"zeroFill":false},{"autoIncrement":false,"binary":false,"blob":false,"collationIndex":255,"columnLabel":"password","databaseName":"test","decimals":0,"encoding":"UTF-8","flags":0,"fromFunction":false,"fullName":"t_user.password","javaType":12,"length":1020,"multipleKey":false,"mysqlType":"VARCHAR","mysqlTypeId":253,"name":"password","notNull":false,"originalName":"password","originalTableName":"t_user","primaryKey":false,"readOnly":false,"singleBit":false,"tableName":"t_user","uniqueKey":false,"unsigned":false,"valueNeedsQuoting":true,"zeroFill":false},{"autoIncrement":false,"binary":false,"blob":false,"collationIndex":255,"columnLabel":"phone","databaseName":"test","decimals":0,"encoding":"UTF-8","flags":0,"fromFunction":false,"fullName":"t_user.phone","javaType":12,"length":1020,"multipleKey":false,"mysqlType":"VARCHAR","mysqlTypeId":253,"name":"phone","notNull":false,"originalName":"phone","originalTableName":"t_user","primaryKey":false,"readOnly":false,"singleBit":false,"tableName":"t_user","uniqueKey":false,"unsigned":false,"valueNeedsQuoting":true,"zeroFill":false}]}
                JSONObject jsonObject = JSON.parseObject(metaDataJson);
                if (!jsonObject.isEmpty()) {
                    System.err.println("columnCount =>" + jsonObject.getInteger("columnCount"));
                    JSONArray fields = jsonObject.getJSONArray("fields");
                    if (null != fields) {
                        for (int i = 0; i < fields.size(); i ++) {
                            JSONObject field = fields.getJSONObject(i);
                            System.err.println("autoIncrement : " + field.getBoolean("autoIncrement")
                                    + ", binary : " + field.getBoolean("binary")
                                    + ", blob : " + field.getBoolean("blob")
                                    + ", collationIndex : " + field.getInteger("collationIndex")
                                    + ", columnLabel: " + field.getString("columnLabel")
                                    + ", databaseName: " + field.getString("databaseName")
                                    + ", decimals: " + field.getInteger("decimals")
                                    + ", encoding: " + field.getString("encoding")
                                    + ", flags: " + field.getInteger("flags")
                                    + ", fromFunction: " + field.getBoolean("fromFunction")
                                    + ", fullName: " + field.getString("fullName")
                                    + ", javaType: " + field.getInteger("javaType")
                                    + ", length: " + field.getInteger("length")
                                    + ", multipleKey: " + field.getBoolean("multipleKey")
                                    + ", mysqlType: " + field.getString("mysqlType")
                                    + ", mysqlTypeId: " + field.getInteger("mysqlTypeId")
                                    + ", name: " + field.getString("name")
                                    + ", notNull: " + field.getBoolean("notNull")
                                    + ", originalName: " + field.getString("originalName")
                                    + ", originalTableName: " + field.getString("originalTableName")
                                    + ", primaryKey: " + field.getBoolean("primaryKey")
                                    + ", readOnly: " + field.getBoolean("readOnly")
                                    + ", singleBit: " + field.getBoolean("singleBit")
                                    + ", tableName: " + field.getString("tableName")
                                    + ", uniqueKey: " + field.getBoolean("uniqueKey")
                                    + ", unsigned: " + field.getBoolean("unsigned")
                                    + ", valueNeedsQuoting: " + field.getBoolean("valueNeedsQuoting")
                                    + ", zeroFill: " + field.getBoolean("zeroFill")
                            );
                        }
                    }
                }

                System.err.println(metaDataJson);
                int columnCount = metaData.getColumnCount();
                List<DbColumn> tableColumns = Lists.newArrayList();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    String columnLabel = metaData.getColumnLabel(i);
                    String columnClassName = metaData.getColumnClassName(i);
                    int columnType = metaData.getColumnType(i);
                    String columnTypeName = metaData.getColumnTypeName(i);
                    int columnDisplaySize = metaData.getColumnDisplaySize(i);
                    String schemaName = metaData.getSchemaName(i);
                    String tableName = metaData.getTableName(i);

                    DbColumn dbColumn = new DbColumn(columnName, columnLabel, columnClassName,
                            columnType, columnTypeName, columnDisplaySize, schemaName, tableName);
                    tableColumns.add(dbColumn);

                    System.err.println("dbColumn => " + dbColumn);
                    System.err.printf("%s %s %s %s %s %s %s\n",
                            i, columnName, columnLabel, columnClassName, columnType, columnTypeName, columnDisplaySize);
                }

                List<Map<String, Object>> result = new ArrayList<>();
                while (rs.next()) {
                    Map<String, Object> map = Maps.newHashMap();
                    for(DbColumn dbColumn : tableColumns) {
                        String columnLabel = dbColumn.getColumnLabel();
                        String columnClassName = dbColumn.getColumnClassName();
//                        Class<?> clasz = Class.forName(columnClassName);
//                        Object object = clasz.newInstance();
//                        Object value = null;
//                        if (object instanceof Integer) {
//                            value = (Integer) rs.getInt(columnLabel);
//                        }
//                        else if(object instanceof String) {
//                            value = (String) rs.getString(columnLabel);
//                        }
//                        else if(object instanceof Date) {
//                            value = (Date) rs.getDate(columnLabel);
//                        }
//                        else if(object instanceof Double) {
//                            value = (Double) rs.getDouble(columnLabel);
//                        }
//                        else if(object instanceof Long) {
//                            value = (Long) rs.getLong(columnLabel);
//                        }
//                        else if(object instanceof Float) {
//                            value = (Float) rs.getFloat(columnLabel);
//                        }
//                        else if(object instanceof Array) {
//                            value = (Array) rs.getArray(columnLabel);
//                        }
//                        else if(object instanceof Blob) {
//                            value = (Blob) rs.getBlob(columnLabel);
//                        }
//                        else if(object instanceof Clob) {
//                            value = (Clob) rs.getClob(columnLabel);
//                        }
//                        else if(object instanceof Time) {
//                            value = (Time) rs.getTime(columnLabel);
//                        }
//                        else if(object instanceof Timestamp) {
//                            value = (Timestamp) rs.getTimestamp(columnLabel);
//                        }
//                        else {
//                            value = rs.getObject(columnLabel);
//                        }

//                        map.put(columnLabel, rs.getObject(columnLabel));
                        map.put(columnLabel, rs.getObject(columnLabel));
                    }

                    map.put("userId", rs.getInt("userId"));
                    map.put("userName", rs.getString("userName"));
                    map.put("password", rs.getString("password"));
                    map.put("phone", rs.getString("phone"));
                    result.add(map);
                    System.err.println(JSON.toJSONString(map));
                }


                pstmt.close();
                rs.close();
                connection.close();
                data.put("result", result);
            }

        }
        catch (Exception e) {
            e.printStackTrace();
            data.put("error", e.getMessage());
        }

        return data;
    }
}
