package com.example.drago.model.dto;

import com.example.drago.model.entity.Users;

public class UserResponse {

	private String username;
	private String fullname;
	private String phone;
	private String address;
	
	UserResponse(){}
	
	public UserResponse(Users user){
		this.username = user.getUsername();
		this.fullname = user.getFullname();
		this.phone = user.getPhone();
		this.address = user.getAddress();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
