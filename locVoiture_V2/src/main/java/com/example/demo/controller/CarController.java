package com.example.demo.controller;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Car;
import com.example.demo.entity.Rental;
import com.example.demo.service.CarService;
import com.example.demo.service.RentalService;

@Controller
public class CarController {
	
	@Autowired
	private CarService carS;
	
	@Autowired 
	private RentalService rentalS;
	
	
	@GetMapping("/manager/addCar")
	public String addCar() {
		return "add_car.html";
	}
	@PostMapping("/manager/addNewCar")
	public String addNewCar(@ModelAttribute Car car, BindingResult result, RedirectAttributes redirectAttributes,
            @RequestParam("techVisit") String techVisitString) {
		
		 LocalDate techVisit = LocalDate.parse(techVisitString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	        car.setTechVisit(techVisit);
		if(carS.existCar(car.getMatricule())) {
			  redirectAttributes.addFlashAttribute("carError", "This car already exist");
		}else 
		{
		    carS.addCar(car);
		    
		    redirectAttributes.addFlashAttribute("message", "Car added successfully");
		}



	    return "redirect:/manager/addCar"; // Redirect to a confirmation page
	}

	@GetMapping("/car/{id}")
	public String viewCarDetails(@PathVariable int id, Model model) {
	    Car car = carS.getCarById(id);
	    model.addAttribute("car", car);
	    return "carDetails"; //
	
	}

  

  
	
	@GetMapping("/home")
	public String listCars(Model mdl) {
		List<Car> cars = carS.getAllCars();
		mdl.addAttribute("cars",cars);
		return "home2";
	}
	
	@GetMapping("/")
	public String listCarsBeforeLogin(Model mdl) {
		List<Car> cars = carS.getAllCars();
		mdl.addAttribute("cars",cars);
		return "home";
	}
	
	
	
	
	@GetMapping("/manager/allcars")
	public String allCars(Model mdl) {
		List<Car> allcars = carS.getAllCars();
		mdl.addAttribute("allcars",allcars);
		return "list_cars";
	}
	
	
	
	@GetMapping("/delete/car/{id}")
	public String deleteCar(@PathVariable int id , Model mdl) {
		carS.deleteCar(id);
		return "redirect:/manager/allcars";
	}
	
	@GetMapping("/manager/editcar/{id}")
	public String editCar(@PathVariable int id , Model mdl) {
		Car cs=carS.getCarById(id);
		mdl.addAttribute("cs", cs);
		return "edit_car";
	}
	
	@PostMapping("/updateCar")
	public String updateCar(@ModelAttribute Car updateCar) {

	    // Retrieve the existing car by ID
	    Car existingCar = carS.getCarById(updateCar.getID_C());

	    if (existingCar != null) {
	        // Add some logging to see the values
	        System.out.println("Existing car: " + existingCar.toString());

	        // Update the car information with the new values
	        existingCar.setModel(updateCar.getModel());
	        existingCar.setMatricule(updateCar.getMatricule());
	        existingCar.setColor(updateCar.getColor());
	        existingCar.setNbDoors(updateCar.getNbDoors());
	        existingCar.setPrice(updateCar.getPrice());
	        existingCar.setTechVisit(updateCar.getTechVisit());
	        existingCar.setDescription(updateCar.getDescription());

	        // Update the car in the database
	        carS.addCar(existingCar);

	        // Add some logging to see the updated values
	        System.out.println("Updated car: " + existingCar.toString());
	    }

	    return "redirect:/manager/allcars";
	}


	
	

}
