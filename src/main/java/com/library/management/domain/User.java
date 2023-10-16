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
@Data
@AllArgsConstructor
@Table(name = "user_master")
public class User {

	@Setter
	@Column(name = "user_name")
	private String userName;

	@Column(name = "password")
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

}
