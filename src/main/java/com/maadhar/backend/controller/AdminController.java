package com.maadhar.backend.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maadhar.backend.pojo.Aadhar;
import com.maadhar.backend.pojo.Admin;
import com.maadhar.backend.pojo.Citizen;
import com.maadhar.backend.services.AadharServices;
import com.maadhar.backend.services.AdminService;
import com.maadhar.backend.services.CitizenService;
import com.maadhar.backend.services.WaitingCitizenService;


@RestController
@CrossOrigin(origins = "*") 
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	AdminService adminService;
	@Autowired
	CitizenService citizenService;
	@Autowired
	WaitingCitizenService waitingCitizenService;
	@Autowired
	AadharServices aadharService;
	
	//Admin Operations
	
	//REGISTER
//	@PostMapping("/")
//	public String registerAdmin(@RequestBody Admin admin) {
//		Admin testAdmin=adminService.getAdminByEmail(admin.getEmail());
//		if (testAdmin!=null) return "0";
//		if (adminService.addAdmin(admin) != null)
//			return "1";
//		else
//			return "0";
//
//	}
	//GET ADMIN
	@GetMapping("/{email}")
	public Admin getAdminByEmail(@PathVariable String email) {
		return adminService.getAdminByEmail(email);
	}
	
	//UPDATE ADMIN DETAILS
	@PutMapping("/{email}")
	public Admin updateAdmin(@PathVariable String email, @RequestBody Admin admin) {
		Admin testAdmin= adminService.getAdminByEmail(email);
		if (testAdmin==null) return null;
		Long id=testAdmin.getId();
		admin.setId(id);
		return(adminService.editAdmin(admin));
	}
	
	//ADMIN LOGIN
	@PutMapping("/login/{email}/{password}")
	public String adminLogIn(@PathVariable String email, @PathVariable String password) {
		System.out.println(email+"  "+password);
		Admin testAdmin=adminService.getAdminByEmailAndPassword(email,password);
		if (testAdmin==null)
				return "0";
		testAdmin.setIsLoggedIn(true);
		adminService.editAdmin(testAdmin);
		return "1";
	}
	
	//ADMIN LOGOUT
	@PutMapping("logout/{email}")
	public String adminLogOut(@PathVariable String email) {
		Admin testAdmin=adminService.getAdminByEmail(email);
		if (testAdmin==null)
				return "Oops!";
		testAdmin.setIsLoggedIn(false);
		adminService.editAdmin(testAdmin);
		return "Logged out!";
	}
	
//	 CITIZEN USER OPERATIONS
	@GetMapping("/waiting")
	public List<Citizen> getWaitingCitizens(){
		List<String> waitingCitizensEmail= waitingCitizenService.getWaitingCitizensEmail();
		List<Citizen> waitingCitizens=new ArrayList<>();
		for(String email:waitingCitizensEmail) {
			waitingCitizens.add(citizenService.getCitizenByEmail(email));
		}
		return waitingCitizens;
	}
	@PutMapping("/approve/{email}")
	public String approveCitizenAadhar(@PathVariable String email) {
		Citizen citizen=citizenService.getCitizenByEmail(email);
		if(citizen==null)
			return "Citizen not found!";
		String aadharNumber=aadharService.randomAadharNumberGenerator();
		
		while(isAadharNumberExists(aadharNumber)) {
			aadharNumber=aadharService.randomAadharNumberGenerator();
		}
		Aadhar aadhar=new Aadhar();
		aadhar.setId(aadharNumber);
		aadhar.setIssueDate(LocalDate.now());
		aadhar.setStatus("issued");		
		aadhar.setCitizen(citizen);
		
		aadharService.addAadhar(aadhar);
		citizen.setStatus("approved");
		
		citizenService.editCitizen(citizen);
		
		waitingCitizenService.removeWaitingCitizen(email);
		return "Aadhar Number Allocated... Aadhar Number ="+aadharNumber+", Citizen Name ="+citizen.getName();
		
	}
	//METHOD TO CHECK IF AADHAR NUMBER HAS ALREADY BEEN ALLOCATED TO A CITIZEN
	public boolean isAadharNumberExists(String aadharNumber) {
		Citizen testCitizen=citizenService.getCitizenByAadharNumber(aadharNumber);
		return(testCitizen!=null);
	}
	
	
	
	
	@PutMapping("/reject/{email}")
	public String rejectCitizenAadhar(@PathVariable String email) {
		Citizen citizen=citizenService.getCitizenByEmail(email);
		if(citizen==null)
			return "Citizen not found!";
		citizen.setStatus("rejected");
		waitingCitizenService.removeWaitingCitizen(email);
		return "Aadhar request rejected";
	}
	
	@PutMapping("/notAlive/{email}")
	public String updateDeadCitizen(@PathVariable String email) {
		Citizen citizen=citizenService.getCitizenByEmail(email);
		if(citizen==null)
			return "0";
		citizen.setIsAlive(false);
		return "1";
	}
	
}