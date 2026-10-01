package com.dev.jwt.services;

import com.dev.jwt.dto.AuthResponse;
import com.dev.jwt.dto.LoginRequestDto;
import com.dev.jwt.dto.RegisterRequestDto;

public interface LoginService {

	void registerUser(RegisterRequestDto registerRequest);

	AuthResponse authenticateUser(LoginRequestDto LoginRequest);
	
}
