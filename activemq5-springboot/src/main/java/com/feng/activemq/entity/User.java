package com.feng.activemq.entity;

/**
 * 用户信息
 * @author
 * @date 2020/03/09
 */
public class User {

    private String userId;

    private String userName;

    private Integer userAge;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userAge=" + userAge +
                '}';
    }

    public User() {
    }

    public User(String userId, String userName, Integer userAge) {
        this.userId = userId;
        this.userName = userName;
        this.userAge = userAge;
    }
}
