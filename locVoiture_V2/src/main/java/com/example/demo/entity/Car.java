package com.example.demo.entity;




import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="voiture")
public class Car {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int ID_C;
	private String matricule;
	private String model;
	private String color;
	private int nbDoors;
	private double price;
	@Column(name = "techVisit")
	private LocalDate  techVisit;
	private String description;
	
	
	@OneToMany(mappedBy = "car")
    private List<Rental> rentals;
	
	
 
	public List<Rental> getRentals() {
		return rentals;
	}
	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	
	public LocalDate getTechVisit() {
		return techVisit;
	}
	public void setTechVisit(LocalDate techVisit) {
		this.techVisit = techVisit;
	}
	public int getID_C() {
		return ID_C;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setID_C(int iD_C) {
		ID_C = iD_C;
	}

	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getNbDoors() {
		return nbDoors;
	}
	public void setNbDoors(int nbDoors) {
		this.nbDoors = nbDoors;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
    public boolean isAvailable(LocalDate startDate, LocalDate endDate) {
        // Assuming rentals is a List<Rental> field in the Car entity
        for (Rental rental : rentals) {
            LocalDate rentalStartDate = rental.getReservationStartDate();
            LocalDate rentalEndDate = rental.getReservationEndDate();

            // Check for overlap
            if (startDate.isBefore(rentalEndDate) && endDate.isAfter(rentalStartDate)) {
                return false; // Not available
            }
        }
        return true; // Available
    }
	
	
	
	

}
