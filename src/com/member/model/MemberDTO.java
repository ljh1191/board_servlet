package com.member.model;

public class MemberDTO {
	private int num;
	private String id, pass, name, age, gender, email;
	public int getNum() {
		return num;
	}
	public String getId() {
		return id == null ? "" : id.trim();
	}
	public String getPass() {
		return pass == null ? "" : pass.trim();
	}
	public String getName() {
		return name == null ? "" : name.trim();
	}
	public String getAge() {
		return age == null ? "" : age.trim();
	}
	public String getGender() {
		return gender == null ? "" : gender.trim();
	}
	public String getEmail() {
		return email == null ? "" : email.trim();
	}
	public void setNum(int num) {
		this.num = num;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
