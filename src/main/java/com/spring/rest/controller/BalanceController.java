package com.spring.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {
	
	
	
	@GetMapping("/Balance")
	public String myBalance()
	{
		return "This is my balance";
	}

}
