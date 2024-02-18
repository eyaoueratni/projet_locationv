package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Contract;
import com.example.demo.entity.Rental;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.RentalRepository;

@Service
public class ContractService {
	
	@Autowired
	private ContractRepository cRep;
	
	@Autowired
	private RentalRepository rRep;
	
	public void saveContract(Contract c) {
		cRep.save(c);
    }
	
	public  List<Contract> getAllContracts(){
		return cRep.findAll();
		}
	
	
	
	
	 public void deleteContract(int id) {
	     cRep.deleteById((long) id);
	        
	    }
	

}
