package com.devops.dto;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author lujxu
 *
 */
public class RiskDTO {
	
	private String rid;
    private String tid;
    private String name;
    private int createTime;
    private int updateTime;
    private int status;
	private String description;
    
    private List<RiskTracingDTO> traceUserList = new ArrayList<>();
    
    /**
	 * 
	 * @param tracingUser
	 */
    public void addTracingUser(RiskTracingDTO tracingUser){
    	this.traceUserList.add(tracingUser);
    }
    
	public String getRid() {
		return rid;
	}
	public String getTid() {
		return tid;
	}
	public String getName() {
		return name;
	}
	public int getCreateTime() {
		return createTime;
	}
	public List<RiskTracingDTO> getTraceUserList() {
		return traceUserList;
	}
	public int getUpdateTime() {
		return updateTime;
	}
	public int getStatus() {
		return status;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}
	public void setTraceUserList(List<RiskTracingDTO> traceUserList) {
		this.traceUserList = traceUserList;
	}
	public void setUpdateTime(int updateTime) {
		this.updateTime = updateTime;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
