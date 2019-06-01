package com.feng.arangodb.entity;

import com.arangodb.entity.DocumentField;

/**
 * 人际关系信息
 */
public class Relationship {

    @DocumentField(DocumentField.Type.ID)
    private String id;

    @DocumentField(DocumentField.Type.KEY)
    private String key;

    @DocumentField(DocumentField.Type.FROM)
    private String from;

    @DocumentField(DocumentField.Type.TO)
    private String to;

    private String label;

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

    public Relationship() {
    }

    public Relationship(String key, String from, String to, String label) {
        this.key = key;
        this.from = from;
        this.to = to;
        this.label = label;
    }
}
