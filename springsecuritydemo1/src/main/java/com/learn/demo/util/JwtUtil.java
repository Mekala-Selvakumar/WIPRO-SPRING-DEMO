package com.learn.demo.util;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private final String SECRET = "Secret-Key-32-byte-WIPRO-JAVA--FSD-Success!";

	private Key getKey() {
		return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
	}
	
	public String generateToken(String  username) {
		return  Jwts.builder()
				.subject(username)
				.issuedAt(new Date())
				.expiration(
						new Date(System.currentTimeMillis()+1000*60*60) )
				.signWith(getKey())
				.compact();
	}

	public String extractUsername(String token) {
		return Jwts.parser()
				.verifyWith((SecretKey)getKey())
				.build()
				.parseSignedClaims(token)
				.getPayload()
				.getSubject();
	}
	
	public  boolean validateToken(String  token, String username) {
		return extractUsername(token).equals(username);
	}
}
