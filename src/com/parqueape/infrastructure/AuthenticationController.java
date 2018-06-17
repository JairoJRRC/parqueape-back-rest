package com.parqueape.infrastructure;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.parqueape.application.AuthenticationFactory;
import com.parqueape.application.CompanyService;
import com.parqueape.application.EmployeeService;
import com.parqueape.application.UserService;
import com.parqueape.domain.EnumRole;
import com.parqueape.domain.User;

@Path("/login")
public class AuthenticationController {

	static AuthenticationFactory factory;

	public static AuthenticationFactory authentication(EnumRole role) {

		switch (role) {
		case COMPANY:
			factory = new CompanyService();
			break;
		case EMPLOYEE:
			factory = new EmployeeService();
			break;
		case ADMIN:
		default:
			break;
		}
		return factory;
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public static Response authentication(@FormParam("email") String email, @FormParam("password") String password) throws Exception {
		
		EnumRole role = getRoleByUser(email, password);
		
		AuthenticationFactory factory = authentication(role);
		User registeredUser = factory.isValidUser(email, password);
		factory.isValidPassword(registeredUser.getPassword(), password);
		JSONObject result = factory.getDataUser(registeredUser);
		
		String message = "El login fue exitoso.";

		return Response.status(200).entity(PresentationUtil.response(message, result)).build();

	}

	public static EnumRole getRoleByUser(String email, String password) {
		User user = null;
		user = UserService.findByEmailAndPassword(email, password);
		return user.getRole();
	}
}
