package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="Manager")
public class Manager {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int ID_M;
	private String fullname;
	private String email;
	private String username;
	private String password;
	
	
	

	public int getID_M() {
		return ID_M;
	}
	public void setID_M(int iD_M) {
		ID_M = iD_M;
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
	@Override
	public String toString() {
		return "Manager [ID_M=" + ID_M + ", fullname=" + fullname + ", email=" + email + ", username=" + username
				+ ", password=" + password + "]";
	}
	
	

	

	
	
}



