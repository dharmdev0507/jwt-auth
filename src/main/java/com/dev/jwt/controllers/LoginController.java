package com.dev.jwt.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.jwt.dto.AuthResponse;
import com.dev.jwt.dto.LoginRequestDto;
import com.dev.jwt.dto.RegisterRequestDto;
import com.dev.jwt.services.LoginService;

@RestController
@RequestMapping("/api")
public class LoginController {

	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	private final LoginService loginService;

	public LoginController(LoginService loginService) {
		super();
		this.loginService = loginService;
	}

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody RegisterRequestDto registerRequest) {

		loginService.registerUser(registerRequest);
		log.info("User registered successfully");
		return ResponseEntity.ok("User registered successfully");
	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> authenticateUser(@RequestBody LoginRequestDto loginRequest) {

		AuthResponse jwtToken = loginService.authenticateUser(loginRequest);
		log.info("Token generated");
		return ResponseEntity.ok(jwtToken);
	}

}
