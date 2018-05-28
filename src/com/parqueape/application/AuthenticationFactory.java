package com.parqueape.application;

import com.parqueape.domain.User;

public abstract class AuthenticationFactory {
	
	public User isValidUser(String email, String role) {
		User user = null;
		return user;
	}
	
	public Boolean isValidPassword(User user) {
		return null;
	}
	
	
	public abstract String login(String email, String password);
	
}
