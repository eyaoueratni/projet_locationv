package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Car;
import com.example.demo.entity.Client;
import com.example.demo.service.ClientService;
@Controller
public class ClientController {
	@Autowired
	private ClientService clientS;
	
/*	@GetMapping("/")
	public String home(Model mdl) {
		
		return "home.html";
		
	}*/
	
	
	@GetMapping("/add")
	public String addClientForm()
	{
		return "register_client.html";
	}
	/*@PostMapping("/register")
	public String registerClient(@ModelAttribute Client c) {
		System.out.println(c);
		
		clientS.addClient(c);
		return "home.html";
		
	}
	*/
	@PostMapping("/register")
	public String registerClient(@ModelAttribute Client c, RedirectAttributes redirectAttributes) {
	    System.out.println(c);

	    // Password validation
	    String passwordRegex = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=]).{6,}$";
	   
	    if (!c.getPassword().matches(passwordRegex)) {
	        redirectAttributes.addFlashAttribute("passwordError", "Password must be 6 characters alphanumeric with at least one special character");
	        return "redirect:/add";
	    }
	    
	    if (clientS.existUser(c.getUsername())) {
	        redirectAttributes.addFlashAttribute("usernameError", "Username already exists. Please choose a different username.");
	        return "redirect:/add";
	    }

	    clientS.addClient(c);
	    return "login_Client";
	}

	@GetMapping("/login")
	public String logClient()
	{
		return "login_Client.html";
	}
	
	@PostMapping("/loginClient")
	public String login(@RequestParam String username, @RequestParam String password, Model model) {
	    Client admin = clientS.findByUsername(username);

	    if (admin != null && clientS.verifyPassword(admin, password)) {
	        // Successfully logged in
	        // You can add user-specific logic here or redirect to another page
	        return "redirect:/home";
	    } else {
	        // Login failed, display an error message
	        model.addAttribute("error", "Invalid username or password");
	        return "login_Client.html";
	    }
	}
	


	
  }
	
