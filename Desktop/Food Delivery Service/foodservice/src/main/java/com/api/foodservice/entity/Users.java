package com.api.foodservice.entity;
 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
 
@Entity
public class Users {
	@Id
	@Column(name ="userid")
	private String userId;
	private String password;
	private String role;
	public Users() {}
	public Users(String userId, String password, String role) {
		super();
		this.userId = userId;
		this.password = password;
		this.role = role;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
