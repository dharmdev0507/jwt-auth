package com.dev.jwt.exceptions;

public class UsernameExistsException extends RuntimeException{

	public UsernameExistsException(String msg) {
		super(msg);
	}

}
