package com.example.drago.services.impl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import com.example.drago.services.TokenService;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

	private final String S_SELF = "self";
	private final String S_SCOPE = "scope";
	
	private final JwtEncoder jwtEncoder;
	
	public TokenServiceImpl(JwtEncoder jwtEncoder) {
		this.jwtEncoder = jwtEncoder;
	}
	
	public String generateToken(Authentication authentication) throws Exception{
		try {
			Instant now = Instant.now();
			String scope = authentication.getAuthorities().stream()
					.map(GrantedAuthority::getAuthority)
					.collect(Collectors.joining(" "));
			JwtClaimsSet claims = JwtClaimsSet.builder()
					.issuer(S_SELF)
					.issuedAt(now)
					.expiresAt(now.plus(1, ChronoUnit.HOURS))
					.subject(authentication.getName())
					.claim(S_SCOPE, scope)
					.build();
			return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
		}catch(Exception e) {
			throw new Exception(e);
		}
	}	
}
