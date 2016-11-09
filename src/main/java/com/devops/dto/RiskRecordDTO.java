package com.devops.dto;
/**
 * 
 * @author lujxu
 *
 */
public class RiskRecordDTO {
	
	  	private String rrid;
	    private String rid;
	    private int createTime;

	    private String content;
	    private int possibility;
	    private int affection;
	    private String trigger;
	    private String traceUserId;
	    private String traceUserName;
	    
		public String getRrid() {
			return rrid;
		}
		public String getRid() {
			return rid;
		}
		public int getCreateTime() {
			return createTime;
		}
		public String getContent() {
			return content;
		}
		public int getPossibility() {
			return possibility;
		}
		public int getAffection() {
			return affection;
		}
		public String getTrigger() {
			return trigger;
		}
		public String getTraceUserId() {
			return traceUserId;
		}
		public String getTraceUserName() {
			return traceUserName;
		}
		public void setRrid(String rrid) {
			this.rrid = rrid;
		}
		public void setRid(String rid) {
			this.rid = rid;
		}
		public void setCreateTime(int createTime) {
			this.createTime = createTime;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public void setPossibility(int possibility) {
			this.possibility = possibility;
		}
		public void setAffection(int affection) {
			this.affection = affection;
		}
		public void setTrigger(String trigger) {
			this.trigger = trigger;
		}
		public void setTraceUserId(String traceUserId) {
			this.traceUserId = traceUserId;
		}
		public void setTraceUserName(String traceUserName) {
			this.traceUserName = traceUserName;
		}
	    
	    

}
