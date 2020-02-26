package com.john.price.PetAdoption.Services;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Component
public class JWTService {
	
	private static final String SECRET = "HawtSecret";
	private static final long EXPIRATION_TIME = 864_000_000;
	
	public String createToken(Authentication authentication) {
		return JWT.create()
                .withSubject(((User) authentication.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(getAlgorithm());
	}
	
	public String getUsernameFromToken(String token) {
		return JWT.require(getAlgorithm())
                .build()
                .verify(token)
                .getSubject();
	}
	
	private Algorithm getAlgorithm() {
		return HMAC512(SECRET.getBytes());
	}
}
