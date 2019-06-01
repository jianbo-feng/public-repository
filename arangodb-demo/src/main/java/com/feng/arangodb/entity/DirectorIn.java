package com.feng.arangodb.entity;

import com.arangodb.entity.DocumentField;

/**
 * 导演-电影关系
 */
public class DirectorIn {

    @DocumentField(DocumentField.Type.ID)
    private String id;

    @DocumentField(DocumentField.Type.KEY)
    private String key;

    private String roles;

    private String year;

    private String label;

    @DocumentField(DocumentField.Type.FROM)
    private String from;

    @DocumentField(DocumentField.Type.TO)
    private String to;

    public DirectorIn() {
    }

    public DirectorIn(String key, String roles, String year, String label, String from, String to) {
        this.key = key;
        this.roles = roles;
        this.year = year;
        this.label = label;
        this.from = from;
        this.to = to;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
