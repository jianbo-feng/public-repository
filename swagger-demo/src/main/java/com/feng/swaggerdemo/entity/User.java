package com.feng.swaggerdemo.entity;

public class User {

    private String id;

    private String name;

    private String pwd;

    private int gender;

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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public User() {
    }

    public User(String id, String name, String pwd, int gender) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.gender = gender;
    }
}
