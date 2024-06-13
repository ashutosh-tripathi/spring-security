package com.spring.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoansController {

	@GetMapping("/Loans")
	public String myLoans() {
		return "These are my loans";
	}
}
