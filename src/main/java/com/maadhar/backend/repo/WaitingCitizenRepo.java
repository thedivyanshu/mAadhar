package com.maadhar.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maadhar.backend.pojo.WaitingCitizen;

public interface WaitingCitizenRepo extends JpaRepository<WaitingCitizen, String>{

}
