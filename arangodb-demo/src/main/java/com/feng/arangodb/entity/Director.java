package com.feng.arangodb.entity;

import com.arangodb.entity.DocumentField;

/**
 * 导演信息
 */
public class Director {

    @DocumentField(DocumentField.Type.ID)
    private String id;

    @DocumentField(DocumentField.Type.KEY)
    private String key;

    private String name;

    private String born;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Director() {
    }

    public Director(String key, String name, String born, String label) {
        this.key = key;
        this.name = name;
        this.born = born;
        this.label = label;
    }
}
