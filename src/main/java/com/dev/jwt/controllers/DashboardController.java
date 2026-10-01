package com.dev.jwt.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

	@GetMapping("/admin")
	public String getAdminPage() {
		return "Hello, Admin";
	}

	@GetMapping("/main")
	public String LandingPage() {
		return "Hello, Main Page";
	}
}
