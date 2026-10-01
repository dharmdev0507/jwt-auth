package com.dev.jwt.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dev.jwt.dto.AuthResponse;
import com.dev.jwt.dto.LoginRequestDto;
import com.dev.jwt.dto.RegisterRequestDto;
import com.dev.jwt.entity.UserEntity;
import com.dev.jwt.exceptions.UsernameExistsException;
import com.dev.jwt.repository.UserRepository;
import com.dev.jwt.security.JwtService;

@Service
public class LoginServiceImpl implements LoginService{
	
	private static final Logger log=LoggerFactory.getLogger(LoginServiceImpl.class);
	
	private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public LoginServiceImpl(ModelMapper modelMapper,UserRepository userRepository, PasswordEncoder passwordEncoder,
    		AuthenticationManager authenticationManager,JwtService jwtService) {
		super();
		this.modelMapper=modelMapper;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager=authenticationManager;
		this.jwtService=jwtService;
	}

    @Override
	public void registerUser(RegisterRequestDto registerRequest) {

        if (userRepository.existsByUsername(
        		registerRequest.getUsername())) {
            log.error("Username already exists");
            throw new UsernameExistsException(
                    "Username already exists"
            );
        }

        UserEntity user = modelMapper.map(registerRequest, UserEntity.class);
        user.setPassword(
                passwordEncoder.encode(
                		registerRequest.getPassword()
                )
        );
        
        log.info("users details saved");
        userRepository.save(user);
    }

	@Override
	public AuthResponse authenticateUser(LoginRequestDto loginRequest) {
		
		Authentication authentication =authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		UserDetails userDetails =(UserDetails) authentication.getPrincipal();
		String token =jwtService.generateJwtToken(userDetails);
        log.info("generate the jwt token");
        return new AuthResponse(token);
	}
}
