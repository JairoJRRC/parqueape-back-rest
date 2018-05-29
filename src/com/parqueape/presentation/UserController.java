package com.parqueape.presentation;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.parqueape.application.CompanyService;
import com.parqueape.domain.Company;
import com.parqueape.domain.EnumRole;
import com.parqueape.domain.User;

public class UserController {
	
	@GET
	@Path("/data")
	public static String data() {

		User us = User.create(EnumRole.company, "jairo_rafa1997@hotmail.com", "12345678", new Date());

		Long id = com.parqueape.application.UserService.create(us);
	
		Company c = Company.create(1235678978, "Tecnología", "922031342", "Jairo S.A.C", id);
		CompanyService.create(c);
		
		
		return "ok";
	}
}
