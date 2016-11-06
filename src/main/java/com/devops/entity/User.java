package com.devops.entity;

/**
 * Created by super on 2016/11/6.
 */
public class User {
    private String uid;
    // the name of user
    private String name;
    // login name
    private String username;
    private String password;

    // 0 means worker, 1 means manager
    private int level;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
