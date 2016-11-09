package com.devops.dto;
/**
 * 
 * @author lujxu
 *
 */
public class UserDTO {
	
	private String uid;
	/** the name of user **/
	private String name;
	/** login name **/
	private String username;
	
	public String getUid() {
		return uid;
	}
	public String getName() {
		return name;
	}
	public String getUsername() {
		return username;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "UserDTO : {uid:" + uid + ", name:" + name + ", username:" + username + "}";
	}
	
	
}
