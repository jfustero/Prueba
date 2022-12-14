package com.example.drago.services;

import org.springframework.security.core.Authentication;

public interface TokenService {

	public String generateToken(Authentication authentication) throws Exception;

}

