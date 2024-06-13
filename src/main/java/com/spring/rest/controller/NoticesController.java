package com.spring.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticesController {
	
	
	@GetMapping("/Notices")
	public String getNotices() {
		return "These are my notices";
	}

}
