package com.parqueape.infrastructure;

import java.util.Date;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.parqueape.application.CompanyService;
import com.parqueape.application.EmployeeService;
import com.parqueape.application.GarageService;
import com.parqueape.application.SiteService;
import com.parqueape.application.UserService;
import com.parqueape.domain.Company;
import com.parqueape.domain.Employee;
import com.parqueape.domain.EnumRole;
import com.parqueape.domain.Garage;
import com.parqueape.domain.Site;
import com.parqueape.domain.User;

@Path("/data")
public class UserController {
	
	@GET
	public static String data() {

		User us = User.create(EnumRole.COMPANY, "jairo_rafa1997@hotmail.com", "12345678", new Date());

		Long id = UserService.create(us);
	
		Company c = Company.create(1235678978, "Tecnología", "922031342", "Jairo S.A.C", id);
		CompanyService.create(c);
		
		Garage g = Garage.create("garage pepit", "12313,-4524", "sadasdas", "png", c);	
		GarageService.create(g);
		
		Site s = Site.create("disponible", g);
		SiteService.create(s);
		
		Employee em = Employee.create(new Date(), (float) 2000.00, new Date(), EnumTurn.MORNING, "98798798798797", "Jairo Rojas", "Rojas Rojas", EnumTypeDoc.DNI, "32165487", "png", c);
		EmployeeService.create(em);
		return "ok";
	}
}
