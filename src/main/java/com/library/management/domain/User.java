package com.library.management.domain;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Table(name = "USER_MASTER")
public class User {

	@Setter
	@Column(name = "user_name")
	private String userName;
	
	private String password;
	
	 @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "user_role")
	private String userRole;
	
	@CreationTimestamp
    @Column(name = "created_time", updatable = false)
	private Date userCreationTime;
	
	public String getuserName() {
		return userName;
	}
	
	public void setUserName(String username) {
		this.userName = username;
	}
	
	public String getUserRole() {
		return userRole;
	}
	
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password= password;
	}

	
}
