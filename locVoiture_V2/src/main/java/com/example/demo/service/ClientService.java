package com.example.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Car;
import com.example.demo.entity.Client;
import com.example.demo.repository.ClientRepository;
@Service
public class ClientService {
	@Autowired
	private ClientRepository clientRep;
	

	

	public void addClient(Client c) {
		
		 clientRep.save(c);
	}
	public Client findByUsername(String username) {
		return clientRep.findByUsername(username);
	}
	
	public boolean existUser(String username) {
		return clientRep.existsByUsername(username);
	}
	
	public boolean verifyPassword(Client client, String plainTextPassword) {
	    String pwd = client.getPassword();
	    return pwd.equals(plainTextPassword);
	}
	
	
	public Client getClientById(int id) {
		java.util.Optional<Client> client = clientRep.findById(id);
		if(client.isPresent()) {
			return client.get();
		}
		return null;
	}






}
