package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.example.demo.entity.Car;

import com.example.demo.entity.Contract;
import com.example.demo.entity.Rental;
import com.example.demo.service.CarService;

import com.example.demo.service.ContractService;
import com.example.demo.service.RentalService;

import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.List;


@Controller
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @Autowired
    private CarService carService;
    

    @Autowired
    private ContractService cService;
    
   

   /* @GetMapping("/rentals")
    public String getAllRentals(Model model) {
        List rentals = rentalService.getAllRentals();
        model.addAttribute("rentals", rentals);
        return "rentals";
    }
*/
    @GetMapping("/rentalForm")
    public String showRentalForm(@RequestParam("carId") int carId, Model model) {
        // Retrieve the car from the database using the carId
        Car car = carService.getCarById(carId);

        // Create a new Rental object and set the car
        Rental rental = new Rental();
        rental.setCar(car);
        
        

        // Add the rental object to the model
        model.addAttribute("rental", rental);

        return "rentalForm";
    }


 // RentalController.java

    @PostMapping("/rental/add")
    public String addRental(@ModelAttribute Rental rental, @RequestParam("carId") int carId ,  Model model) {
        // Set the car for the rental by retrieving it from the database using carId
        Car car = carService.getCarById(carId);
        rental.setCar(car);
        
        
   
       
        
        LocalDate startDate = rental.getReservationStartDate();
        LocalDate endDate = rental.getReservationEndDate();

        if (!car.isAvailable(startDate, endDate)) {
            // Car is not available for the selected date range
            model.addAttribute("errorMessage", "Selected car is not available for the specified dates.");
            return "errorPage"; // Create a Thymeleaf template for displaying errors
        }
        
        rental.calculateTotalPrice();

        // Add the rest of your logic to save the rental
        rentalService.saveRental(rental);
        model.addAttribute("rental", rental);
        model.addAttribute("totalPrice", rental.getTotalPrice());
        
        Contract contract = new Contract();
        contract.setRental(rental);
        
        model.addAttribute("contract", contract);
        
        cService.saveContract(contract);

        return "rentalDetails";
    }
    
    @GetMapping("/manager/rentals")
    public String getAllRentals(Model mdl) {
    	List<Rental> allRentals = rentalService.getAllRentals();
    	mdl.addAttribute("allRentals",allRentals);
    	return "list_rentals";
    }
    
    @GetMapping("/manager/deleteRental/{id}")
    public String deleteRental(@PathVariable int id) {
        rentalService.deleteRental(id);
        return "redirect:/manager/rentals";
    }

}
