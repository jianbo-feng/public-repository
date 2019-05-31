package com.feng.arangodb.entity;

import com.arangodb.entity.DocumentField;

/**
 * 电影信息
 */
public class Movie {

    @DocumentField(DocumentField.Type.ID)
    private String id;

    @DocumentField(DocumentField.Type.KEY)
    private String key;

    private String title;

    private String released;

    private String tagline;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Movie() {
    }

    public Movie(String key, String title, String released, String tagline, String label) {
        this.key = key;
        this.title = title;
        this.released = released;
        this.tagline = tagline;
        this.label = label;
    }
}
