package com.maadhar.backend.pojo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Aadhar {
	@Id
	private String id;
	
	@OneToOne
	@JoinColumn(name="citizen_id")
	private Citizen citizen;
	
	private LocalDate issueDate;
	private String status;
   
}
