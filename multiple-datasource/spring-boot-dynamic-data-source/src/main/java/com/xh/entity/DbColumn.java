package com.xh.entity;

import java.io.Serializable;

/**
 * 字段信息
 */
public class DbColumn implements Serializable {

    private String columnName;

    private String columnLabel;

    private String columnClassName;

    private int columnType;

    private String columnTypeName;

    private int columnDisplaySize;

    private String schemaName;

    private String tableName;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnLabel() {
        return columnLabel;
    }

    public void setColumnLabel(String columnLabel) {
        this.columnLabel = columnLabel;
    }

    public String getColumnClassName() {
        return columnClassName;
    }

    public void setColumnClassName(String columnClassName) {
        this.columnClassName = columnClassName;
    }

    public int getColumnType() {
        return columnType;
    }

    public void setColumnType(int columnType) {
        this.columnType = columnType;
    }

    public String getColumnTypeName() {
        return columnTypeName;
    }

    public void setColumnTypeName(String columnTypeName) {
        this.columnTypeName = columnTypeName;
    }

    public int getColumnDisplaySize() {
        return columnDisplaySize;
    }

    public void setColumnDisplaySize(int columnDisplaySize) {
        this.columnDisplaySize = columnDisplaySize;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public DbColumn() {
    }

    public DbColumn(String columnName, String columnLabel, String columnClassName, int columnType,
                    String columnTypeName, int columnDisplaySize, String schemaName, String tableName) {
        this.columnName = columnName;
        this.columnLabel = columnLabel;
        this.columnClassName = columnClassName;
        this.columnType = columnType;
        this.columnTypeName = columnTypeName;
        this.columnDisplaySize = columnDisplaySize;
        this.schemaName = schemaName;
        this.tableName = tableName;
    }

    @Override
    public String toString() {
        return "DbColumn{" +
                "columnName='" + columnName + '\'' +
                ", columnLabel='" + columnLabel + '\'' +
                ", columnClassName='" + columnClassName + '\'' +
                ", columnType=" + columnType +
                ", columnTypeName='" + columnTypeName + '\'' +
                ", columnDisplaySize=" + columnDisplaySize +
                ", schemaName='" + schemaName + '\'' +
                ", tableName='" + tableName + '\'' +
                '}';
    }
}
