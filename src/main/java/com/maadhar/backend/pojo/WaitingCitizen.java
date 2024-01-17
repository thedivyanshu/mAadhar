package com.maadhar.backend.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
@Entity
@Data
public class WaitingCitizen {
	@Id
	private String emailId;

	@Override
	public String toString() {
		return emailId;
	}

}
