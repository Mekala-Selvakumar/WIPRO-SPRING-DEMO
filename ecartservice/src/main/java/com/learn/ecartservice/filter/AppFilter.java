package com.learn.ecartservice.filter;

import com.learn.ecartservice.controller.CartController;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AppFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String url =request.getRequestURI();
		System.out.println(url);
		String header = request.getHeader("Authorization");
		System.out.println(header);
//		 Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE3ODA1NzUzMzAsImV4cCI6MTc4MDU3ODkzMCwic3ViIjoidG9tQGdtYWlsLmNvbSJ9.qkx5R5Pnt5UbR4TrfBxIg5LEDPelR3W_Oo416TW8V_Q
 		
		if (header!=null && !header.isBlank() && header.length()>8 && header.startsWith("Bearer ")) {
			String token = header.substring(7);
			SecretKey key =Keys.hmacShaKeyFor("Secret-Key-32-byte-WIPRO-JAVA--FSD-Success!"
					.getBytes(StandardCharsets.UTF_8));
	
			try {
				Claims claims =Jwts.parser().verifyWith(key).build()
						.parseSignedClaims(token).getPayload();
				System.out.println("Claims . : " +claims);
				String emailId =claims.getSubject();
				System.out.println("Email Id : " +emailId);
				if (emailId!=null && !emailId.isBlank()) {
					filterChain.doFilter(request, response);
				}
				else {
					response.sendError(HttpStatus.UNAUTHORIZED.value(),"Invalid Token......");
				}
			}catch(ExpiredJwtException e) {
				System.out.println("Token expired at : "+e.getClaims().getExpiration() );
 				response.sendError(HttpStatus.UNAUTHORIZED.value(),e.getMessage());
			}
			catch(JwtException e) {
				System.out.println("Invalid JWT : " +e.getMessage());
 				response.sendError(HttpStatus.UNAUTHORIZED.value(),e.getMessage());
			}
			catch(Exception e) {
				System.out.println(e.getClass());
				response.sendError(HttpStatus.UNAUTHORIZED.value(),"Invalid Token......");
			}
		}
		else {
			response.sendError(HttpStatus.UNAUTHORIZED.value(),"Invalid Token......");

		}
		filterChain.doFilter(request, response);
	}
 }
