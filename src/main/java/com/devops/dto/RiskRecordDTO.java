package com.devops.dto;

import java.sql.Timestamp;

public class RiskRecordDTO {
	
	  	private String rrid;
	    private String rid;
	    private Timestamp createTime;

	    private String content;
	    private int possibility;
	    private int affection;
	    private String trigger;
	    private String userId;
	    private String username;
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
		public Timestamp getCreateTime() {
			return createTime;
		}
		public void setCreateTime(Timestamp createTime) {
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
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
	    
	    

}
