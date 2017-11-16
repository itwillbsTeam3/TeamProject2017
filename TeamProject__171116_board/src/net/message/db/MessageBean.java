package net.message.db;

import java.sql.Timestamp;

public class MessageBean {
	private int num;
	private String rid;
	private String sid;
	private String message;
	private int flag;
	private Timestamp date;
	

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getRid() {
		return rid;
	}
	public String getSid() {
		return sid;
	}

	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getMessage() {
		return message;
	}
	public int getFlag() {
		return flag;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
}
