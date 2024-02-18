package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Admin;
import com.example.demo.service.AdminService;
@Controller
public class AdminController {
	
	@Autowired
	private AdminService adminS;
	
	
	@GetMapping("/admin")
	public String welcomPage() {
		
		return "dashboardAdmin";
	}
	
	@GetMapping("/admin/users")
	public String listAdmins(Model mdl) {
		List<Admin> admins = adminS.getAllAdmins();
		mdl.addAttribute("admins",admins);
		return "list_admins";
	}
	
	
	
	
	@GetMapping("/admin/login")
	public String loginAdmin() {
		return "admin_login.html";
	}
	
	
	@PostMapping("/loginAdmin")
	public String login(@RequestParam String username, @RequestParam String password, Model model) {
	    Admin admin = adminS.findByUsername(username);

	    if (admin != null && adminS.verifyPassword(admin, password)) {
	        // Successfully logged in
	        // You can add user-specific logic here or redirect to another page
	        return "dashboardAdmin.html";
	    } else {
	        // Login failed, display an error message
	        model.addAttribute("error", "Invalid username or password");
	        return "admin_login.html";
	    }
	}
	
	@GetMapping("/edit/{id}")
	public String editAdmin(@PathVariable int id , Model mdl) {
		
		Admin ad=adminS.getAdminById(id);
		mdl.addAttribute("ad",ad);
		return "edit_admin";
	}
	
	@PostMapping("/update")
	/*public String updateAdmin(@ModelAttribute Admin admin) {
		
		adminS.addAdmin(admin);
		return "dashboardAdmin";

	}*/
	
	
	public String updateAdmin(@ModelAttribute Admin updatedAdmin) {
	    // Retrieve the existing admin from the database
	    Admin existingAdmin = adminS.getAdminById(updatedAdmin.getID_A());

	    if (existingAdmin != null) {
	        // Add some logging to see the values
	        System.out.println("Existing Admin: " + existingAdmin.toString());

	        // Update the fields of the existing admin with the new values
	        existingAdmin.setFullname(updatedAdmin.getFullname());
	        existingAdmin.setEmail(updatedAdmin.getEmail());
	        existingAdmin.setUsername(updatedAdmin.getUsername());
	        existingAdmin.setPassword(updatedAdmin.getPassword());

	        // Save the updated admin
	        adminS.addAdmin(existingAdmin);

	        // Add some logging to see the updated values
	        System.out.println("Updated Admin: " + existingAdmin.toString());
	    }

	    return "redirect:/admin/users";
	}




	
	@GetMapping("/delete/{id}")
	public String deleteAdmin(@PathVariable int id) {
		
		adminS.deleteAdmin(id);
		return "redirect:/admin/users";
	}
	

}
