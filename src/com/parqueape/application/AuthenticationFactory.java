package com.parqueape.application;

import org.json.JSONObject;
import com.parqueape.domain.User;

public abstract class AuthenticationFactory {
	
	private static User user = null;
	private static UserService userService = null;

	public User isValidUser(String email, String password) throws Exception {
		return userService.findByEmailAndPassword(email, password);
	}

	public Boolean isValidPassword(String password, String passwordInput) throws Exception {

		if (!password.equals(passwordInput)) {
			throw new Exception("El email o password no son válidos.");
		}
		return true;
	}

	public abstract JSONObject getDataUser(User user);

}
