package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Manager;
import com.example.demo.repository.ManagerRepository;

@Service
public class ManagerService {
	
	@Autowired 
	private ManagerRepository managerRep;
	
	
	public void addManager(Manager m) {
		managerRep.save(m);
	}
	

	
	
	public Manager findByUsername(String username) {
		return managerRep.findByUsername(username);
	}
	
	public boolean verifyPassword(Manager manager, String plainTextPassword) {
	    String pwd = manager.getPassword();
	    return pwd.equals(plainTextPassword);
	}
	
	public List<Manager> getAllManagers(){
		return managerRep.findAll();	
		}
	
	public Manager getManagerId(int id) {
		java.util.Optional<Manager> manager = managerRep.findById(id);
		if(manager.isPresent()) {
			return manager.get();		}
		return null;
		
	}
	
	public void deleteManager(int id) {
		managerRep.deleteById(id);
	}
	

}
