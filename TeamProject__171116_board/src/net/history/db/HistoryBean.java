package net.history.db;

import java.sql.Date;
import java.sql.Timestamp;

public class HistoryBean {
	private int flag,num,Rnum;
	private String id,host_id,guest_id;
	private int mileage,price;
	private Date date,app_date,agree_date;
	
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getRnum() {
		return Rnum;
	}
	public void setRnum(int Rnum) {
		this.Rnum = Rnum;
	}
	public String getGuest_id() {
		return guest_id;
	}
	public void setGuest_id(String guest_id) {
		this.guest_id = guest_id;
	}
	public String getHost_id() {
		return host_id;
	}
	public void setHost_id(String host_id) {
		this.host_id = host_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getMileage() {
		return mileage;
	}
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	public Date getApp_date() {
		return app_date;
	}
	public void setApp_date(Date app_date) {
		this.app_date = app_date;
	}
	public Date getAgree_date() {
		return agree_date;
	}
	public void setAgree_date(Date agree_date) {
		this.agree_date = agree_date;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
