package com.spring.rest.config;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.spring.rest.constant.SecurityConstant;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTTokenFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		if(authentication!=null) {
//			SecretKey key=Keys.hmacShaKeyFor(SecurityConstant.JWT_KEY.getBytes());
			SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
			String jwt=Jwts.builder().setIssuer("JWTTOKENFACTORY").setSubject("JWTTOKEN")
					.claim("username",authentication.getName())
					.claim("authoriy",populateAuthorities(authentication.getAuthorities()))
					.setIssuedAt(new Date())
					.setExpiration(new Date(new Date().getTime()+3000000))
					.signWith(key).compact();
			response.setHeader(SecurityConstant.JWT_HEADER, jwt);
		}
		filterChain.doFilter(request, response);
	}
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		return request.getServletPath().equalsIgnoreCase("/login");
		
	}
	
	public String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
		Set<String> authoritiesSet=new HashSet<>();
		for(GrantedAuthority authority:collection) {
			authoritiesSet.add(authority.getAuthority());
		}
		return String.join(",", authoritiesSet);
	}

}
