package com.parqueape.infrastructure;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.EntityMode;
import org.json.JSONObject;

import com.parqueape.application.CompanyService;
import com.parqueape.application.UserService;
import com.parqueape.domain.Company;
import com.parqueape.domain.EnumRole;
import com.parqueape.domain.User;

@Path("/company")
public class CompanyController {
	
	private static User user = null;
	private static Company company = null;
	
	@POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public static Response register(
			@FormParam("email") String email,
			@FormParam("password") String password,
			@FormParam("ruc") String ruc,
			@FormParam("phoneNumber") String phoneNumber,
			@FormParam("tradeName") String tradeName
	) {
		try {
			UserService.validateExistUser(email, EnumRole.COMPANY);
		} catch (Exception e) {
			return Response.status(400).entity(PresentationUtil.error(e.getMessage()))
					.build();
		}
		user = User.create(EnumRole.COMPANY, email, password, new Date());
		Long userId = UserService.create(user);
		
		company = Company.create(Integer.parseInt(ruc), phoneNumber, tradeName, userId);
		Long companyId = CompanyService.create(company);
		
		return Response.status(200).entity(PresentationUtil.response("La empresa se registro correctamente.",
				new JSONObject().append("id", companyId))).header("Access-Control-Allow-Origin", "*").build();		
	}
	
	@PUT
	@Path("/{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public static Response update(
			@FormParam("ruc") String ruc,
			@FormParam("phoneNumber") String phoneNumber,
			@FormParam("tradeName") String tradeName,
			@PathParam("id") String id
	) {
		
		Company company = CompanyService.findById(Long.parseLong(id));
		company.setRuc(Integer.parseInt(ruc));
		company.setPhoneNumber(phoneNumber);
		company.setTradeName(tradeName);
		CompanyService.update(company);
		
		
		return Response.status(200)
				.entity(PresentationUtil.response("La empresa fue actualizada correctamente.",
						new JSONObject().append("id", company.getId())))
				.header("Access-Control-Allow-Origin", "*").build();		
	}
	
	@GET
	@Path("/user/{userId}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public static Response getByUserId(
			@PathParam("userId") String userId
	) {
		Company company = CompanyService.findByUserId(Long.parseLong(userId));
		JSONObject result = company.getObject();
		
		return Response.status(200).entity(PresentationUtil.response("La empresa se obtuvo correctamente.", result))
				.header("Access-Control-Allow-Origin", "*").build();		
	}
	
	@GET
	@Path("/{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public static Response get(
			@PathParam("id") String id
	) {
		Company company = CompanyService.findById(Long.parseLong(id));
		JSONObject result = company.getObject();
		
		return Response.status(200).entity(PresentationUtil.response("LLa empresa se obtuvo correctamente.", result))
				.header("Access-Control-Allow-Origin", "*").build();		
	}
	
	
	@DELETE
	@Path("/{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public static Response delete(
			@PathParam("id") String id
	) {
		CompanyService.delete(Long.parseLong(id));
		
		return Response.status(200)
				.entity(PresentationUtil.response("La empresa se eliminó correctamente.", new JSONObject()))
				.header("Access-Control-Allow-Origin", "*").build();		
	}
}
