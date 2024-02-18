package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.ManagerService;
import com.example.demo.entity.Manager;

@Controller
public class ManagerController {
	
	@Autowired
	private ManagerService managerS;
	
	@GetMapping("/manager")
	public String welcomeManager() {
		
		return "dashboardManager";
	}
	
	@GetMapping("/admin/managers")
	public String listManagers(Model mdl) {
		List<com.example.demo.entity.Manager> managers =managerS.getAllManagers();
		mdl.addAttribute("managers" , managers);
		return "list_managers";
	}
	
	
	@GetMapping("/manager/login")
	public String loginManager() {
		return "manager_login.html";
	}
	
	@PostMapping("/managerlogin")
	public String login(@RequestParam String username, @RequestParam String password, Model model) {
	   
	    
	    com.example.demo.entity.Manager manager = managerS.findByUsername(username);

	    if (manager != null && managerS.verifyPassword(manager, password)) {
	        // Successfully logged in
	        // You can add user-specific logic here or redirect to another page
	        return "dashboardManager.html";
	    } else {
	        // Login failed, display an error message
	        model.addAttribute("error", "Invalid username or password");
	        return "manager_login.html";
	    }
	}
	
	@GetMapping("/delete/manager/{id}")
	public String deleteManager(@PathVariable int id) {
		managerS.deleteManager(id);
		return "redirect:/admin/managers";
	}
	
	@GetMapping("/edit/manager/{id}")
	public String editManager(@PathVariable int id , Model mdl) {
		Manager mn=managerS.getManagerId(id);
		mdl.addAttribute("mn",mn);
		return "edit_manager";
		
	}
	
	@PostMapping("/updateManager")
	public String updateManager(@ModelAttribute Manager updateManager) {
		
	    Manager existingManager = managerS.getManagerId(updateManager.getID_M());

	    if (existingManager != null) {
	        // Add some logging to see the values
	        System.out.println("Existing manager: " + existingManager.toString());

	        existingManager.setFullname(updateManager.getFullname());
	        existingManager.setEmail(updateManager.getEmail());
	        existingManager.setUsername(updateManager.getUsername());
	        existingManager.setPassword(updateManager.getPassword());

	        managerS.addManager(existingManager);

	        // Add some logging to see the updated values
	        System.out.println("Updated manager: " + existingManager.toString());
	    }

	    return "redirect:/admin/managers";
	}
	
}
