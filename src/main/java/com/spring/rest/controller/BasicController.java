package com.spring.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.model.Users;
import com.spring.rest.repository.UsersRepository;

@RestController
public class BasicController {
	
	@Autowired
	UsersRepository usersRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping("welcome")
	public String check() {
		return "This is safe spring application";
	}

	@PostMapping("/addUsers")
	public ResponseEntity addUsers(@RequestBody Users users) {
		
		users.setPwd(passwordEncoder.encode(users.getPwd()));
		usersRepository.save(users);
		return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(users);
	}
	
	
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody Users users) {
		
		
		return ResponseEntity.status(HttpStatusCode.valueOf(200)).body("Your login is successful");
	}
}
