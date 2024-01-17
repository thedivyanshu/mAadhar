package com.maadhar.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.maadhar.backend.pojo.Citizen;

public interface CitizenRepo extends JpaRepository<Citizen, Long> {
public Citizen getByEmailId(String emailId);
	
	public Citizen getByMobileNo(String mobileNo);

	@Query("SELECT user FROM Citizen user WHERE user.emailId=?1 AND user.mobileNo=?2")
	public Citizen getByEmailIdAndMobile(String email, String mobile);

	@Query("SELECT user FROM Citizen user JOIN user.aadhar  ua WHERE ua.id=?1")
	public Citizen getByAadharNumber(String aadharNumber);


}
