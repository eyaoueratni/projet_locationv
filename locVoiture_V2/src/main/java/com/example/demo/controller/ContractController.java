package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.Contract;
import com.example.demo.service.ContractService;


@Controller
public class ContractController {
	
	@Autowired 
	private ContractService cnService;
	
	
	@GetMapping("/manager/contracts")
	public String getAllContracts(Model mdl) {
		
		List <Contract> contarcts=cnService.getAllContracts();
    	mdl.addAttribute("contarcts",contarcts);
		return "list_contracts";
	}
	
	  @GetMapping("/manager/deleteContract/{id}")
	    public String deleteContract(@PathVariable int id) {
	        cnService.deleteContract(id);
	        return "redirect:/manager/rentals";
	    }
	
	

}
