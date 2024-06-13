package com.spring.rest.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.rest.model.Users;
import com.spring.rest.repository.UsersRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UsersRepository usersRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<Users> users=usersRepository.findByEmail(username);
		if(users==null || users.size()==0) {
			throw new UsernameNotFoundException("User is not found in the system");
		}
		else
		{
			List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority(users.get(0).getRole()));
			return new User(users.get(0).getEmail(),users.get(0).getPwd(),authorities);
		}
	}

}
