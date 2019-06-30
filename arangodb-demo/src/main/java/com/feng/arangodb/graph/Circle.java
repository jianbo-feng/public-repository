package com.feng.arangodb.graph;

import com.arangodb.entity.DocumentField;
import com.arangodb.entity.DocumentField.Type;

/**
 * 圆
 */
public class Circle {

    @DocumentField(Type.ID)
    private String id;

    @DocumentField(Type.KEY)
    private String key;

    @DocumentField(Type.REV)
    private String revision;

    private String label;

    public Circle(String key, String label) {
        this.key = key;
        this.label = label;
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

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
