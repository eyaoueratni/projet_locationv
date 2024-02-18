package com.example.demo.entity;




import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="location")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rentalId;
    
    @ManyToOne
    @JoinColumn(name = "carId")
    private Car car;
    
 

   // private String clientFullName;
    
    private String clientFullName;
    
    public String getClientFullName() {
		return clientFullName;
	}

	public void setClientFullName(String clientFullName) {
		this.clientFullName = clientFullName;
	}

	private int CIN;
    private int phone;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate  reservationStartDate;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate  reservationEndDate;

    
    private double totalPrice;


    @OneToOne(mappedBy = "rental" ,cascade = CascadeType.REMOVE)
    private Contract contract;

    public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public void calculateTotalPrice() {
        if (car != null && reservationStartDate != null && reservationEndDate != null) {
            long days = ChronoUnit.DAYS.between(reservationStartDate, reservationEndDate) + 1;
            totalPrice = days * car.getPrice();
        } else {
            totalPrice = 0.0;
        }
    }
    
	public double getTotalPrice() {
		return totalPrice;
	}



	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}



	public int getRentalId() {
		return rentalId;
	}



	public void setRentalId(int rentalId) {
		this.rentalId = rentalId;
	}




	public LocalDate  getReservationStartDate() {
		return reservationStartDate;
	}



	public void setReservationStartDate(LocalDate  reservationStartDate) {
		this.reservationStartDate = reservationStartDate;
	}



	public LocalDate  getReservationEndDate() {
		return reservationEndDate;
	}



	public void setReservationEndDate(LocalDate  reservationEndDate) {
		this.reservationEndDate = reservationEndDate;
	}



	



	public Car getCar() {
		return car;
	}






	public void setCar(Car car) {
		this.car = car;
	}

	public int getCIN() {
		return CIN;
	}

	public void setCIN(int cIN) {
		CIN = cIN;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	

   
}
