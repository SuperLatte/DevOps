package com.devops.entity;

import com.devops.controller.UserController;

import java.util.List;

/**
 * Created by super on 2016/11/6.
 */
public class Risk {
	
    private String rid;
    private String tid;

    private String name;
    private int createTime;
    private int updateTime;

    // 1 means opened, 0 means closed
    private int status = 1;

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCreateTime() {
        return createTime;
    }

    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }

    public int getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(int updateTime) {
        this.updateTime = updateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
