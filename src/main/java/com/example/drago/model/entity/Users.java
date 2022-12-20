package com.example.drago.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name="Users")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", length=5)
	private Integer id;
	@Column(name="user", length=20)
	private String username;
	@Column(name="fullname", length=50)
	private String fullname;
	@Column(name="phone", length=15)
	private String phone;
	@Column(name="address", length=150)
	private String address;
	@Column(name="password", length=25)
	private String password;

	public Users() {}
	
	public Users(String username, String fullname, String phone, String address, String password) {
		this.username = username;
		this.fullname = fullname;
		this.phone = phone;
		this.address = address;
		this.password = password;
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public static Users parse(String line) {
		String[] fileds = line.split(",\\s*");
		return new Users(fileds[0],fileds[1],fileds[2],fileds[3],null);	
	}
	
}
