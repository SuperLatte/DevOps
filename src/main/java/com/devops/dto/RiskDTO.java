package com.devops.dto;

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
    
    private List<RiskTracingDTO> traceUserList;
    
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
	public List<RiskTracingDTO> getTraceUserList() {
		return traceUserList;
	}
	public void setTraceUserList(List<RiskTracingDTO> traceUserList) {
		this.traceUserList = traceUserList;
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
