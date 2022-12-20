package com.example.drago.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.drago.model.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Integer>{

	Users findByUsername(String user);
	
}
