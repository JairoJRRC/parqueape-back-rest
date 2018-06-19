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
	
	public final static String img1 = "https://geo3.ggpht.com/cbk?panoid=DgGFChrmAtJsJAJedM6r2Q&output=thumbnail&cb_client=search.TACTILE.gps&thumb=2&w=80&h=92&yaw=195.97209&pitch=0&thumbfov=100";
	public final static String img2 = "https://geo2.ggpht.com/cbk?panoid=kRHrRoId-ihoRZFqA8gRhA&output=thumbnail&cb_client=search.TACTILE.gps&thumb=2&w=80&h=92&yaw=224.94362&pitch=0&thumbfov=100";
	public final static String img3 = "https://geo3.ggpht.com/cbk?panoid=JKOfS9esDFZVR2tMub5yTg&output=thumbnail&cb_client=search.TACTILE.gps&thumb=2&w=80&h=92&yaw=221.59598&pitch=0&thumbfov=100";
	public final static String img4 = "https://geo3.ggpht.com/cbk?panoid=lWPADKOeo-dwMRJl-BTpfQ&output=thumbnail&cb_client=search.TACTILE.gps&thumb=2&w=80&h=92&yaw=24.46595&pitch=0&thumbfov=100";
	
	@GET
	public static String data() {
		
		

		User us = User.create(EnumRole.COMPANY, "jairo_rafa1997@hotmail.com", "12345678", new Date());
		Long id = UserService.create(us);
		System.out.println("---------------------USUARIO CREADO-------------------------");
		System.out.println("-------------------------------------------------------------------");
	
		Company c = Company.create(1235678978, "922031342", "Jairo S.A.C", id);
		CompanyService.create(c);
		
		System.out.println("---------------------EMPRESA CREADO-------------------------");
		System.out.println("-------------------------------------------------------------------");
		
		Garage g = Garage.create("Garage", "-12.0152211,-77.1114907,13z", "Mz k lt 27 Urb San Martin de Porres, San Juan de Dios, Distrito de Lima 15108", img1, c, 10, new Float(15.00), "asdasd");	
		Garage g1 = Garage.create("Garage 2", "-12.0152211,-77.1114907,13z", "LAP - Aeropuerto Internacional Jorge Chávez, Av. Elmer Faucett, Callao 07031", img2, c, 10, new Float(15.00), "asdasd");	
		Garage g2 = Garage.create("Garage 3", "-12.0152211,-77.1114907,13z", "Av. Tacna 360, Cercado de Lima 15001", img3, c, 10, new Float(15.00), "asdasd");	
		GarageService.create(g);
		GarageService.create(g1);
		GarageService.create(g2);
		
		System.out.println("---------------------GARAGE CREADO-------------------------");
		System.out.println("-------------------------------------------------------------------");
		
		// cuando creas un garage tienes que indicar un numero de sitio por ejemplo 10
		Site s = Site.create("disponible", g);
		Site s1 = Site.create("disponible", g1);
		Site s2 = Site.create("disponible", g2);
		
		for(int i = 0 ; i<10 ; i++) {
			
			SiteService.create(s);
			SiteService.create(s1);
			SiteService.create(s2);
		}
		System.out.println("---------------------SITIO CREADO-------------------------");
		System.out.println("-------------------------------------------------------------------");
		
		//-------------------------------------------------------------------------------------------------
		
		
		User us1 = User.create(EnumRole.EMPLOYEE, "emp1@yopmail.com", "123456", new Date());
		Long id1 = UserService.create(us1);
		User us2 = User.create(EnumRole.EMPLOYEE, "emp2@yopmail.com", "123456", new Date());
		Long id2 = UserService.create(us2);
		User us3 = User.create(EnumRole.EMPLOYEE, "emp3@yopmail.com", "123456", new Date());
		Long id3 = UserService.create(us3);
		
		Employee emp1 = Employee.create(new Date(), (float) 2000.00, new Date(), EnumTurn.MORNING, "98798798798797", "Jairo Rojas", "Rojas Rojas", EnumTypeDoc.DNI, "32165487", "png", c, id1);
		Employee emp2 = Employee.create(new Date(), (float) 2000.00, new Date(), EnumTurn.EVENING, "98798798798797", "Jose Jose", "Rojas Rojas", EnumTypeDoc.DNI, "32165487", "png", c, id2);
		Employee emp3 = Employee.create(new Date(), (float) 2000.00, new Date(), EnumTurn.AFTERNOON, "98798798798797", "Pepe Pepe", "Rojas Rojas", EnumTypeDoc.DNI, "32165487", "png", c, id3);
		EmployeeService.create(emp1);
		EmployeeService.create(emp2);
		EmployeeService.create(emp3);
		
		System.out.println("---------------------EMPLEADO CREADO-------------------------");
		System.out.println("-------------------------------------------------------------------");
		return "ok";
	}
}
