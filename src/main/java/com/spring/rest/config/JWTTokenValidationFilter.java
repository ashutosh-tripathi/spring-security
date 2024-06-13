package com.spring.rest.config;

import java.io.IOException;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.spring.rest.constant.SecurityConstant;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTTokenValidationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String jwt=request.getHeader(SecurityConstant.JWT_HEADER);
		jwt=jwt.split(" ")[1];
		if(jwt!=null) {
//			SecretKey key=Keys.hmacShaKeyFor(SecurityConstant.JWT_KEY.getBytes());
			SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
			Claims claims =Jwts.parser().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
			String username=String.valueOf(claims.get("username"));
			String authorities =String.valueOf(claims.get("authoriy"));
			UsernamePasswordAuthenticationToken auth=new UsernamePasswordAuthenticationToken(username,null, AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		filterChain.doFilter(request, response);
	}
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		return request.getServletPath().equalsIgnoreCase("/login");
		
	}

}
