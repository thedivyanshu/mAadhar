package com.maadhar.backend.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maadhar.backend.pojo.Aadhar;
import com.maadhar.backend.repo.AadharRepo;

@Service
public class AadharServices {

	@Autowired
	AadharRepo repo;
	public String randomAadharNumberGenerator() {
		Random random=new Random();
		long aadharNumber=100_000_000_000L+(random.nextLong()%100_000_000_000L);
		return String.format("%012d", aadharNumber);
	}
	public void addAadhar(Aadhar aadhar) {
		repo.save(aadhar);		
	}
}
