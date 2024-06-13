package com.spring.rest.config;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomFilter  extends  OncePerRequestFilter{
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
	    HttpServletResponse httpResponse = (HttpServletResponse) response;
	    CustomHttpServletRequestWrapper wrappedReq = new CustomHttpServletRequestWrapper(httpRequest);
	    String body = wrappedReq.getBody();
	    System.out.println("CustomFilter: Request URL - " + httpRequest.getRequestURL());
	    if (body.isEmpty()) {
	        httpResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);
	    }
//		filterChain.doFilter(request, response);
	}
}
