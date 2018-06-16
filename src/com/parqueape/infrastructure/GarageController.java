package com.parqueape.infrastructure;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.parqueape.application.CompanyService;
import com.parqueape.application.GarageService;
import com.parqueape.domain.Company;
import com.parqueape.domain.Garage;
import com.parqueape.domain.Site;

@Path("/garage")
public class GarageController {
	
	@POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public static Response register(
			@FormParam("title") String title,
			@FormParam("coordinates") String coordinates,
			@FormParam("address") String address,
			@FormParam("photo") String photo,
			@FormParam("companyId") String companyId
	) {
		
		Company company = CompanyService.findById(Long.parseLong(companyId));
		
		Garage garage = Garage.create(title, coordinates, address, photo, company);
		Long garageId = GarageService.create(garage);
		
		return Response.status(200).entity(PresentationUtil.response("La cochera fue creada exitosamente.", new JSONObject().append("id", garageId))).build();		
	}
	
	@PUT
	@Path("/{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public static Response update(
			@FormParam("title") String title,
			@FormParam("coordinates") String coordinates,
			@FormParam("address") String address,
			@FormParam("photo") String photo,
			@PathParam("id") String id
	) {
		
		Garage garage = GarageService.findById(Long.parseLong(id));
		garage.setTitle(title);
		garage.setCoordinates(coordinates);
		garage.setAddress(address);
		garage.setPhoto(photo);
		GarageService.update(garage);
		
		return Response.status(200).entity(PresentationUtil.response("La cochera se actualizo correctamente.", new JSONObject().append("id", garage.getId()))).build();		
	}
	
//	@GET
//	@Path("/{id}")
//    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//	@Produces("application/json")
//	public static Response getByUserId(
//			@PathParam("id") String id
//	) {
//		Garage garage = GarageService.findById(Long.parseLong(id));
//		JSONObject result = garage.getObject();
//		
//		return Response.status(200).entity(PresentationUtil.response("La cochera se obtuvo correctamente.", result)).build();		
//	}
//	
//	@GET
//	@Path("company/{companyId}")
//    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//	@Produces("application/json")
//	public static Response getByCompanyId(
//			@PathParam("companyId") String companyId
//	) {
//		List<Garage> garages = GarageService.getGarageByCompany(Long.parseLong(companyId));
//		
//		
//		return Response.status(200).entity(PresentationUtil.response("La cochera se obtuvo correctamente.")).build();		
//	}

}
