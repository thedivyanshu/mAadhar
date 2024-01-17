package com.maadhar.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.maadhar.backend.pojo.Admin;

public interface AdminRepo extends JpaRepository<Admin, Long>{
public Admin findByEmail(String email);
	
	@Query("SELECT admin FROM Admin admin WHERE admin.email=?1 AND admin.password=?2")
	public Admin findByEmailAndPassword(String email, String password);
}
