package com.devops.entity;

/**
 * Created by super on 2016/11/6.
 */
public class RiskRecord {
    private String rrid;
    private String rid;
    private int createTime;

    private String content;
    private int possibility;
    private int affection;
    private String trigger;
    private String traceUserid;

    public String getRrid() {
        return rrid;
    }

    public void setRrid(String rrid) {
        this.rrid = rrid;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public int getCreateTime() {
        return createTime;
    }

    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPossibility() {
        return possibility;
    }

    public void setPossibility(int possibility) {
        this.possibility = possibility;
    }

    public int getAffection() {
        return affection;
    }

    public void setAffection(int affection) {
        this.affection = affection;
    }

    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }

    public String getTraceUserid() {
        return traceUserid;
    }

    public void setTraceUserid(String traceUserid) {
        this.traceUserid = traceUserid;
    }
}
