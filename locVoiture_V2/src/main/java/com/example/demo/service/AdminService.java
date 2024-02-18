package com.example.demo.service;

import java.util.List;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.repository.AdminRepository;

import jakarta.transaction.Transactional;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository adminRep;
	
	 
	public void addAdmin(Admin ad) {
		adminRep.save(ad);
	}
	
	
	public Admin findByUsername(String username) {
		return adminRep.findByUsername(username);
	}
	
	public boolean verifyPassword(Admin admin, String plainTextPassword) {
	    String pwd = admin.getPassword();
	    return pwd.equals(plainTextPassword);
	}
	
	public List<Admin> getAllAdmins(){
		return adminRep.findAll();
	}
	
	
	public Admin getAdminById(int id) {
		java.util.Optional<Admin> admin = adminRep.findById(id);
		if(admin.isPresent()) {
			return admin.get();
		}
		return null;
	}
	
	public void deleteAdmin(int id) {
		adminRep.deleteById(id);
	}

}
