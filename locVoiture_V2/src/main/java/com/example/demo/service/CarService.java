
package com.example.demo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.example.demo.entity.Car;
import com.example.demo.repository.CarRepository;



@Service
public class CarService {
	
	@Autowired
	private CarRepository carRep;
	
	public void addCar(Car c) {
		carRep.save(c);
	}

	
	public List<Car> getAllCars(){
		return carRep.findAll();
	}
	
	public boolean existCar(String matricule) {
		return carRep.existsByMatricule(matricule);
	}
	
		public Car getCarById(int id) {
			java.util.Optional<Car> car = carRep.findById(id);
			if(car.isPresent()) {
				return car.get();
			}
			return null;
		}
		
		
		public void deleteCar(int id) {
			carRep.deleteById(id);
		}

	
	
}
