package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="Admin")
public class Admin {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int ID_A;
	private String fullname;
	private String email;
	private String username;
	private String password;
	public int getID() {
		return ID_A;
	}
	
	public int getID_A() {
	    return ID_A;
	}

	public void setID_A(int ID_A) {
	    this.ID_A = ID_A;
	}

	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
	
}



