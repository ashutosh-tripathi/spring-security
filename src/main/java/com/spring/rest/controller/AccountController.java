package com.spring.rest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

	
	
	
@PostMapping("/Account")
public String getAccount() {
	return "Here are details of account";
}
}
