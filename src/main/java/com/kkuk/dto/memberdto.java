package com.kkuk.dto;

public class memberdto {

	private String mid;
	private String mname;
	private String mage;
	private String mdate;
	public memberdto(String mid, String mname, String mage, String mdate) {
		super();
		this.mid = mid;
		this.mname = mname;
		this.mage = mage;
		this.mdate = mdate;
	}
	public memberdto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMage() {
		return mage;
	}
	public void setMage(String mage) {
		this.mage = mage;
	}
	public String getMdate() {
		return mdate;
	}
	public void setMdate(String mdate) {
		this.mdate = mdate;
	}
	
	
}
