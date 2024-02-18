package com.example.demo.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Car;
import com.example.demo.entity.Rental;
import com.example.demo.repository.RentalRepository;

import jakarta.transaction.Transactional;

@Service
public class RentalService {
	
	
	@Autowired
	private RentalRepository rentalRep;
	
  
	
    
    @Transactional
	public void saveRental(Rental rental) {
		rentalRep.save(rental);
    }
    
    public List<Rental> getAllRentals() {
        return rentalRep.findAll();
    }
    
    
    public Rental getRentalById(int id) {
    	java.util.Optional<Rental> r = rentalRep.findById((long) id);
    	if(r.isPresent()) {
    		return r.get();    	}
    
	return null; }
    
    
   public void deleteRental(int id) {
	   rentalRep.deleteById((long) id);
   }
	

	

}
