package com.parqueape.presentation;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.parqueape.application.CompanyService;
import com.parqueape.application.UserService;
import com.parqueape.domain.Company;
import com.parqueape.domain.EnumRole;
import com.parqueape.domain.User;
import com.parqueape.infrastructure.PresentationUtil;

@Path("/company")
public class CompanyController {
	
	private static User user = null;
	private static Company company = null;
	
	@POST
	@Path("/register")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public static Response register(
			@FormParam("email") String email,
			@FormParam("password") String password,
			@FormParam("ruc") Integer ruc,
			@FormParam("businessName") String businessName,
			@FormParam("phoneNumber") String phoneNumber,
			@FormParam("tradeName") String tradeName
	) {
		
		user = User.create(EnumRole.company, email, password, new Date());
		Long userId = UserService.create(user);
		
		company = Company.create(ruc, businessName, phoneNumber, tradeName, userId);
		Long companyId = CompanyService.create(company);
		
		return Response.status(200).entity(PresentationUtil.response("La empresa se registro correctamente.", new JSONObject().append("id", companyId))).build();		
	}
}
