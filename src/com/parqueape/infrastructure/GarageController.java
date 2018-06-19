package com.parqueape.infrastructure;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import com.parqueape.application.CompanyService;
import com.parqueape.application.GarageService;
import com.parqueape.application.SiteService;
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
			@FormParam("companyId") String company_id,
			@FormParam("numberSites") String number_sites,
			@FormParam("pricePerHour") String price_hour,
			@FormParam("description") String description
	) {
		
		Long companyId;
		Integer numberSites;
		Float pricePerHour;

		try {
			companyId = Long.parseLong(company_id);
			numberSites = Integer.parseInt(number_sites);
			pricePerHour = Float.parseFloat(price_hour);
		} catch (Exception e) {
			return Response.status(400).entity(PresentationUtil.error(e.getMessage()))
					.header("Access-Control-Allow-Origin", "*").build();
		}
		
		Company company = CompanyService.findById(companyId);

		Garage garage = Garage.create(title, coordinates, address, photo, company, numberSites, pricePerHour, description);
		Long garageId = GarageService.create(garage);
		
		for (int i = 0; i < numberSites; i++) {
			Site site = Site.create("disponible", garage);
			SiteService.create(site);
		}

		return Response.status(200).entity(PresentationUtil.response("La cochera fue creada exitosamente.",
				new JSONObject().append("id", garageId))).header("Access-Control-Allow-Origin", "*").build();
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
			@FormParam("companyId") String company_id,
			@FormParam("numberSites") String number_sites,
			@FormParam("pricePerHour") String price_hour,
			@FormParam("description") String description,
			@PathParam("id") String id
	) {

		Long garageId;
		Integer numberSites;
		Float pricePerHour;

		try {
			garageId = Long.parseLong(id);
			numberSites = Integer.parseInt(number_sites);
			pricePerHour = Float.parseFloat(price_hour);
		} catch (Exception e) {
			return Response.status(400).entity(PresentationUtil.error(e.getMessage()))
					.header("Access-Control-Allow-Origin", "*").build();
		}
		
		Garage garage = GarageService.findById(garageId);
		garage.setTitle(title);
		garage.setCoordinates(coordinates);
		garage.setAddress(address);
		garage.setPhoto(photo);
		garage.setNumberSites(numberSites);
		garage.setPricePerHour(pricePerHour);
		garage.setDescription(description);
		GarageService.update(garage);

		return Response.status(200)
				.entity(PresentationUtil.response("La cochera se actualizo correctamente.",
						new JSONObject().append("id", garage.getId())))
				.header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public static Response getByUserId(@PathParam("id") String id) {

		Garage garage = GarageService.findById(Long.parseLong(id));
		return Response.status(200)
				.entity(PresentationUtil.response("La cochera se obtuvo correctamente.",
						new JSONObject().put("garage", garage.getObject())))
				.header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Path("/company/{companyId}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public static Response getByCompanyId(
			@PathParam("companyId") String companyId
			) {
		
		List<Garage> garages = GarageService.getGarageByCompany(Long.parseLong(companyId));

		JSONArray arrGarage = new JSONArray();
		if (garages.size() > 0) {
			for (Garage gar : garages) {
				arrGarage.put(gar.getObject());
			}

		}

		return Response.status(200).entity(PresentationUtil.response("Se obtuvo la lista de cocheras correctamente.",
				new JSONObject().put("garages", arrGarage))).header("Access-Control-Allow-Origin", "*").build();
	}
	
	@DELETE
	@Path("/{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public static Response delete(
			@PathParam("id") String id
	) {
		GarageService.delete(Long.parseLong(id));
		
		return Response.status(200)
				.entity(PresentationUtil.response("La cochera se eliminó correctamente.", new JSONObject()))
				.header("Access-Control-Allow-Origin", "*").build();		
	}

}
