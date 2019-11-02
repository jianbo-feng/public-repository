package com.feng.elasticsearch.entity;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.data.elasticsearch.annotations.DateFormat.basic_date_time_no_millis;

@Document(indexName = "docs", type = "test-doc")
public class TestDoc implements Serializable {

    private String id;

    private String name;

    private String content;

    private String type;

    private String roleId;

    @Field(type = FieldType.Date)
    private Date date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public TestDoc() {
    }

    public TestDoc(String id, String name, String content, String type, String roleId, Date date) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.type = type;
        this.roleId = roleId;
        this.date = date;
    }

    @Override
    public String toString() {
        return "TestDoc{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", type='" + type + '\'' +
                ", roleId='" + roleId + '\'' +
                ", date=" + date +
                '}';
    }
}
