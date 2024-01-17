package com.maadhar.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maadhar.backend.pojo.Aadhar;

public interface AadharRepo extends JpaRepository<Aadhar,Long> {
	
}
