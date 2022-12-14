package com.example.drago.services;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.example.drago.entity.Users;

import org.springframework.scheduling.annotation.Async;

public interface UsersService {

	public File getUsers() throws Exception;
		
	public List<Users> copyFile(InputStream inputStream) throws Exception;
	
	@Async("taskExecutor")
	public CompletableFuture<String> aSyncWriteFile(List<Users> listUsers) throws Exception;
	
}
