package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Manager;

public interface ManagerRepository  extends JpaRepository< Manager , Integer> {
	
	Manager findByUsername(String username);

}
