package com.maadhar.backend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maadhar.backend.pojo.WaitingCitizen;
import com.maadhar.backend.repo.WaitingCitizenRepo;

@Service
public class WaitingCitizenService {
	@Autowired
	WaitingCitizenRepo repo;
		public boolean addWaitingCitizen(WaitingCitizen waitingCitizen) {
			if(repo.save(waitingCitizen)!=null)
				return true;
			return false;
		}
		public List<String> getWaitingCitizensEmail() {
			List<WaitingCitizen>waitingList= repo.findAll();
			List<String> waitingEmailList=new ArrayList<>();
			for(WaitingCitizen email: waitingList) {
				waitingEmailList.add(email.toString());
				System.out.println(email.toString());
			}
			return waitingEmailList;
		}
		public void removeWaitingCitizen(String email) {
			repo.deleteById(email);
		}

}
