package com.spring.rest.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.spring.rest.model.Users;
import com.spring.rest.repository.UsersRepository;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	UsersRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		
		String username=authentication.getName();
		String pwd=authentication.getCredentials().toString();
		
		List<Users> users=userRepository.findByEmail(username) ;
		if(users.size()>0) {
			if(passwordEncoder.matches(pwd, users.get(0).getPwd())) {
				List<GrantedAuthority> authorities=new ArrayList<>();
				authorities.add(new SimpleGrantedAuthority(users.get(0).getRole()));
			Authentication auth=new	UsernamePasswordAuthenticationToken(username,pwd,authorities);
				SecurityContextHolder.getContext().setAuthentication(auth);

				return auth;
			}
			else {
				throw new BadCredentialsException("Incorrect password");
			}
			
		}else {
			throw new BadCredentialsException("Incorrect username");
		}
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
