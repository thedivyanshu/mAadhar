package com.maadhar.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maadhar.backend.pojo.Admin;
import com.maadhar.backend.repo.AdminRepo;

@Service
public class AdminService {
	@Autowired
	AdminRepo repo;

	public Admin addAdmin(Admin admin) {
		return repo.save(admin);
	}

	public List<Admin> getAllAdmin() {
		return repo.findAll();
	}

	public Admin getAdminByEmail(String email) {
		return repo.findByEmail(email);
	}

	public Admin editAdmin(Admin admin) {
		return repo.save(admin);
		
	}

	public Admin getAdminByEmailAndPassword(String email, String password) {
		return repo.findByEmailAndPassword(email,password);
	}
}
