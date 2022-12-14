package com.example.drago.utils;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.drago.entity.Users;
import com.example.drago.repositories.UsersRepository;

@Component
public class Runner implements CommandLineRunner{

	private final UsersRepository usersRepository;
	
	public Runner(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}
	
	@Override
	public void run(String... args) throws Exception {

		if(this.usersRepository.count()==0) {
			this.usersRepository.saveAll(List.of(
				new Users("User1", "Thomson", "555-1", "Street 1", "123"),
				new Users("User2", "Simond", "555-2", "Street 2", "456"),
				new Users("User3", "Cliford", "555-3", "Street 3", "789")
			));
		}
		
	}

}
