package com.member.model;

public class GuestDTO {
	private int num;
	private String name,content,grade,created,ipaddr;
	
	public int getNum() {
		return num;
	}
	public String getName() {
		return name == null ? "" : name.trim();
	}
	public String getContent() {
		return content == null ? "" : content.trim();
	}
	public String getGrade() {
		return grade == null ? "" : grade.trim();
	}
	public String getCreated() {
		return created == null ? "" : created.trim();
	}
	public String getIpaddr() {
		return ipaddr == null ? "" : ipaddr.trim();
	}
	public void setNum(int num) {
		this.num = num;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}
}
