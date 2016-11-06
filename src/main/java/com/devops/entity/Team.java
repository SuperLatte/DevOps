package com.devops.entity;

import java.util.List;

/**
 * Created by super on 2016/11/6.
 */
public class Team {
    private String tid;
    private String manager_id;
    private String name;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getManager_id() {
        return manager_id;
    }

    public void setManager_id(String manager_id) {
        this.manager_id = manager_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
