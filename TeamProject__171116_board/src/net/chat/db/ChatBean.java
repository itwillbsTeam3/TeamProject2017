package net.chat.db;

import java.sql.Date;
import java.sql.Timestamp;

public class ChatBean {
	
	int num;
	String fromId;
	String toId;
	String chatContent;
	Timestamp chatTime;
	int chatRead;
	String img;
	
	public int getChatRead() {
		return chatRead;
	}
	public String getImg() {
		return img;
	}
	public void setChatRead(int chatRead) {
		this.chatRead = chatRead;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getFromId() {
		return fromId;
	}
	public void setFromId(String fromId) {
		this.fromId = fromId;
	}
	public String getToId() {
		return toId;
	}
	public void setToId(String toId) {
		this.toId = toId;
	}
	public String getChatContent() {
		return chatContent;
	}
	public void setChatContent(String chatContent) {
		this.chatContent = chatContent;
	}
	public Timestamp getChatTime() {
		return chatTime;
	}
	public void setChatTime(Timestamp chatTime) {
		this.chatTime = chatTime;
	}
	
	
}
