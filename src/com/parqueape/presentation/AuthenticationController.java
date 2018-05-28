package com.parqueape.presentation;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.parqueape.application.AuthenticationFactory;
import com.parqueape.application.CompanyService;

@Path("/login")
public class AuthenticationController {
	static final String ROLE_ADMIN = "admin";
	static final String ROLE_COMPANY = "company";
	static final String ROLE_EMPLOYEE = "employee";
	
	static AuthenticationFactory factory;
	
	public static AuthenticationFactory login(String role) {

		switch (role) {
		case ROLE_ADMIN:
		case ROLE_COMPANY:
		case ROLE_EMPLOYEE:
			factory = new CompanyService();
			break;
		default:
			break;
		}

		return factory;
	}
	
	@POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public static Response execute(
			@FormParam("email") String email,
			@FormParam("password") String password,
			@FormParam("role") String role
	) {
		
		AuthenticationFactory result = login(role);
		
		return Response.status(200).entity(result.login(email, password)).build(); 
		
	}
}
