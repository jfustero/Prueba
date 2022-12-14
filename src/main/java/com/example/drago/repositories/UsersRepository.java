package com.example.drago.repositories;

import com.example.drago.entity.Users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer>{

	Users findByUsername(String user);
	
}
