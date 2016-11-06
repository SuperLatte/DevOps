package com.devops.dto;

import java.util.List;

public class RiskDTO {
	
	private String rid;
    private String tid;
    private String name;
    private int createTime;
    private int updateTime;
    private int status;
    
    private List<String> traceUserList;
    
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
	public List<String> getTraceUserList() {
		return traceUserList;
	}
	public void setTraceUserList(List<String> traceUserList) {
		this.traceUserList = traceUserList;
	}
	@Override
	public String toString() {
		return "RiskDTO : {rid:" + rid + ", tid:" + tid + ", name:" + name + ", createTime:" + createTime
				+ ", updateTime:" + updateTime + ", traceUserList:" + traceUserList + "}";
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
    
    

}
