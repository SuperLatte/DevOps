package com.devops.dto;
/**
 * 
 * @author lujxu
 *
 */
public class TeamDTO {
	
	private String tid;
	private String managerId;
	private String name;

	public String getTid() {
		return tid;
	}
	public String getManagerId() {
		return managerId;
	}
	public String getName() {
		return name;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public void setName(String name) {
		this.name = name;
	}
}
