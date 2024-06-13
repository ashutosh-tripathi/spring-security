package com.spring.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardsController {

	
	@GetMapping("/Cards")
	public String getCards() {
		return "These are my cards";
	}
}
