package com.example.drago.services;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;

import com.example.drago.model.dto.UserResponse;
import com.example.drago.model.entity.Users;

public interface UsersService {

	public File getUsers() throws Exception;
	
	public UserResponse getUser(String user) throws Exception;
	
	public List<Users> copyFile(InputStream inputStream) throws Exception;
	
	@Async("taskExecutor")
	public CompletableFuture<String> aSyncWriteFile(List<Users> listUsers) throws Exception;
	
}
