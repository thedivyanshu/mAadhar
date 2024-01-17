package com.maadhar.backend.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;


@Entity
@Data
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String adminName;
	private String email;   
	private String password;
	private Boolean isLoggedIn=false;

}
