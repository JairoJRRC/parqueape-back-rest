package com.parqueape.application;

public class CompanyService extends AuthenticationFactory{

	@Override
	public String login(String email, String password) {
		return "Soy empresa";
	}

}
