package com.parqueape.infrastructure;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.parqueape.application.CompanyService;
import com.parqueape.application.EmployeeService;
import com.parqueape.application.GarageService;
import com.parqueape.application.ParkingAssignmentService;
import com.parqueape.application.SiteService;
import com.parqueape.application.UserService;
import com.parqueape.domain.Company;
import com.parqueape.domain.Employee;
import com.parqueape.domain.EnumRole;
import com.parqueape.domain.Garage;
import com.parqueape.domain.ParkingAssignment;
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
		
		List<Garage> listGarage = new ArrayList<Garage>();
		listGarage = getListGarage(c);
		int count = 0;
		for (Garage garage : listGarage) {
			GarageService.create(garage);
			Site s = Site.create(garage);
			
			for(int i = 0 ; i<garage.getNumberSites() ; i++) {
				SiteService.create(s);
			}
			count++;
			if(count%2 == 0) {
				ParkingAssignment parkingAssignment = ParkingAssignment.create(emp1.getId(), garage.getId());
				ParkingAssignmentService.create(parkingAssignment);
			} else if (count%3 == 0) {
				ParkingAssignment parkingAssignment = ParkingAssignment.create(emp2.getId(), garage.getId());
				ParkingAssignmentService.create(parkingAssignment);
			} else {
				ParkingAssignment parkingAssignment = ParkingAssignment.create(emp3.getId(), garage.getId());
				ParkingAssignmentService.create(parkingAssignment);
			}
			
		}
		
		System.out.println("---------------------GARAGE CREADO-------------------------");
		System.out.println("-------------------------------------------------------------------");
		
		
		
		
		System.out.println("---------------------SITIO CREADO-------------------------");
		System.out.println("-------------------------------------------------------------------");
		
		//-------------------------------------------------------------------------------------------------
		
		
	
		return "ok";
	}
	
	public static List<Garage> getListGarage(Company c) {
		List<Garage> listGarage = new ArrayList<Garage>();
		
		listGarage.add(Garage.create(
				"Cochera Miraflores",
			"-12.0400265,-77.1080241",
				"Calle Esperanza 224, Miraflores 15074",
			"https://lh5.googleusercontent.com/proxy/i-eaHf6n6CGkPKl9P0ZcjiqqYNNmItl6FCDidtJgM4MQ1zpi9nRWETYZbisY4ZWHvKQjzjhvUp9BWgsryo2OOpABmgwR390j3FkiCA3K1XD8BmhMN5tKu5jFEoD6HAIjkRiFValfdmui4RxWrGETZqjooQ=w408-h306-k-no",
			c, 10, new Float(15.00), "Este comentario es para todos"
			));
			listGarage.add(Garage.create(
				"Los Portales - Parking Lot Alcanfores",
			"-12.0553379,-77.0772984",
				"Calle Alcanfores 140, Miraflores 15074",
			"https://geo3.ggpht.com/cbk?panoid=aiBFN99iXr1ANHRsEMEHog&output=thumbnail&cb_client=search.TACTILE.gps&thumb=2&w=408&h=200&yaw=275.70844&pitch=0&thumbfov=100",
			c, 10, new Float(15.00), "Este comentario es para todos"
			));
			listGarage.add(Garage.create(
				"Estacionamiento Los Portales",
			"-12.1131775,-77.0416547",
				"Cal. Bolivar Nro. 250 Urb. Miraflores, Lima LIMA 18",
			"https://www.google.com/maps/place/Estacionamiento+Los+Portales/@-12.1131775,-77.0416547,14z/data=!4m8!1m2!2m1!1scocheras+cerca+de+Miraflores!3m4!1s0x9105c81c1fa205a1:0x70d35d9a1666e5fd!8m2!3d-12.125843!4d-77.027505",
			c, 10, new Float(15.00), "Este comentario es para todos"
			));
			listGarage.add(Garage.create(
				"Estacionamiento Los Portales",
			"-12.1131764,-77.0416547",
				"Av Jose Larco 1154, Miraflores 15074",
			"https://geo3.ggpht.com/cbk?panoid=dFh3FoL00gKHGbYh6Uz9jA&output=thumbnail&cb_client=search.TACTILE.gps&thumb=2&w=408&h=200&yaw=255.56834&pitch=0&thumbfov=100",
			c, 10, new Float(15.00), "Este comentario es para todos"
			));
			listGarage.add(Garage.create(
				"Cochera London House",
			"-12.1131753,-77.0416547",
				"Calle BolÃvar 148, Miraflores",
			"https://lh5.googleusercontent.com/p/AF1QipMRATmsELzOS1dIOYH8m7SBGD8l8t8FZhXdQamP=w408-h725-k-no",
			c, 10, new Float(15.00), "Este comentario es para todos"
			));
			listGarage.add(Garage.create(
				"Estacionamiento",
			"-12.1131742,-77.0416547",
				"Calle San Martin 151, Miraflores 15074",
			"https://geo2.ggpht.com/cbk?panoid=9cyxOHZ7AH4p7zCBVUUPJQ&output=thumbnail&cb_client=search.TACTILE.gps&thumb=2&w=408&h=200&yaw=6.1215534&pitch=0&thumbfov=100",
			c, 10, new Float(15.00), "Este comentario es para todos"
			));
			listGarage.add(Garage.create(
				"Cochera ViajesClub",
			"-12.1131731,-77.0416548",
				"Calle BolÃvar 130, Miraflores",
			"https://geo3.ggpht.com/cbk?panoid=SSElBSkbrgpiUNvLUo6_KQ&output=thumbnail&cb_client=search.TACTILE.gps&thumb=2&w=408&h=200&yaw=289.30258&pitch=0&thumbfov=100",
			c, 10, new Float(15.00), "Este comentario es para todos"
			));
			listGarage.add(Garage.create(
				"Estacionamiento Miraflores Los Portales",
			"-12.1131731,-77.0416548",
				"Calle BolÃvar 250, Miraflores 15074",
			"https://lh3.googleusercontent.com/proxy/1bHmEzR3NhGLKDX24YZ1fc3GjyqIUrpCIWlrxuRn5zltciMR582ltEntAUBfMIMtKok6PnjaDQ9u3tr-xk2iFQCvOiGRQf74K9CMwjt7XL_Ek5wQPhPeFV7kI6Yo7QFwAe9isD25IsZSsKoW1g83xfhQwg=w408-h229-k-no",
			c, 10, new Float(15.00), "Este comentario es para todos"
			));
			listGarage.add(Garage.create(
				"Cochera Santa Rosa",
			"-12.1131731,-77.0416548",
				"Sta Rosa 728, Lima 15047",
			"https://geo2.ggpht.com/cbk?panoid=mPPBhe5l9EASV_1E5bN1Ag&output=thumbnail&cb_client=search.TACTILE.gps&thumb=2&w=408&h=200&yaw=104.17397&pitch=0&thumbfov=100",
			c, 10, new Float(15.00), "Este comentario es para todos"
			));
			listGarage.add(Garage.create(
				"Cochera Plutarco",
			"-12.1131731,-77.0416548",
				"Calle Esperanza 224, Miraflores 15074",
			"https://geo3.ggpht.com/cbk?panoid=cRwkOmqDmTee606Kk-ehww&output=thumbnail&cb_client=search.TACTILE.gps&thumb=2&w=408&h=200&yaw=209.56277&pitch=0&thumbfov=100",
			c, 10, new Float(15.00), "Este comentario es para todos"
			));
			listGarage.add(Garage.create(
				"Cochera Angamos",
			"-12.1131731,-77.0416548",
				"Av. Angamos Este 758, Surquillo 15047",
			"https://lh3.googleusercontent.com/proxy/9PNIxpuvKhyWv_PRclHkDGBPRbmbDngwtTDgZJPL69DkHz9FNak_c1pFt_SmSVPsoThffQt3VhHOi9XwrXrSUTzovStLMOgjhlQbXy3W6HyreCiT1RGmzdHIxYaGo3BrYUBP6_GT-u-VS98ukgWqsau_dQ=w408-h229-k-no",
			c, 10, new Float(15.00), "Este comentario es para todos"
			));
			listGarage.add(Garage.create(
				"Cochera Cuadra 3 San Borja Sur",
			"-12.1119419,-77.0526155",
				"Av San Borja Sur 335, San Borja 15036",
			"https://geo3.ggpht.com/cbk?panoid=lWPADKOeo-dwMRJl-BTpfQ&output=thumbnail&cb_client=search.TACTILE.gps&thumb=2&w=408&h=200&yaw=24.46595&pitch=0&thumbfov=100",
			c, 10, new Float(15.00), "Este comentario es para todos"
			));
			listGarage.add(Garage.create(
				"Cochera Huallaga",
			"-12.0860397,-77.0685991",
				"Jir€n Huallaga, Cercado de Lima 15001",
			"https://geo3.ggpht.com/cbk?panoid=DgGFChrmAtJsJAJedM6r2Q&output=thumbnail&cb_client=search.TACTILE.gps&thumb=2&w=408&h=200&yaw=195.97209&pitch=0&thumbfov=100",
			c, 10, new Float(15.00), "Este comentario es para todos"
			));
			listGarage.add(Garage.create(
				"Cochera Paruro 1223",
			"-12.0860397,-77.0685991",
				"Paruro 1223, Cercado de Lima 15001",
			"https://geo1.ggpht.com/cbk?panoid=EKfebJg_b-my6h7_FWVu0w&output=thumbnail&cb_client=search.TACTILE.gps&thumb=2&w=408&h=200&yaw=78.22676&pitch=0&thumbfov=100",
			c, 10, new Float(15.00), "Este comentario es para todos"
			));
			listGarage.add(Garage.create(
				"Cochera Rimar Import",
			"-12.0860397,-77.0685991",
				"Jir€n Moquegua 269, Cercado de Lima 15001",
			"https://geo3.ggpht.com/cbk?panoid=JKOfS9esDFZVR2tMub5yTg&output=thumbnail&cb_client=search.TACTILE.gps&thumb=2&w=408&h=200&yaw=221.59598&pitch=0&thumbfov=100",
			c, 10, new Float(15.00), "Este comentario es para todos"
			));
			listGarage.add(Garage.create(
				"Lavado de Autos Cochera 1743",
			"-12.0860397,-77.0685991",
				"Av. Arequipa 1743, Lince 15046",
			"https://lh4.googleusercontent.com/proxy/F27Njmiwh5D5I_Ae2pxmTnCv_m8qI-NS07bVDHzzuVyMZ8nJeSvlDegyX8zqoSrlcCtrcoe83r5cikPGQCnpbQb2nlj5-VYnJ1apLplSB3PpZvLbAdVtS8eJk68uhhOsN6NyIS4PWU2c-q_QgFsA0RQjiw=w408-h229-k-no",
			c, 10, new Float(15.00), "Este comentario es para todos"
			));
			listGarage.add(Garage.create(
				"GalerÃa La Cochera-Rptos Licuadoras",
			"-12.0828637,-77.0540483",
				"Jiron Puno 838, Cercado de Lima 15001",
			"https://lh5.googleusercontent.com/p/AF1QipNhJos9QPYCH8lBztyTOWQLJ79pgdgSaXdCP365=w408-h306-k-no",
			c, 10, new Float(15.00), "Este comentario es para todos"
			));
			listGarage.add(Garage.create(
				"Cochera El Milagro",
			"-12.0860397,-77.0685991",
				"Av. Tacna 360, Cercado de Lima 15001",
			"https://lh5.googleusercontent.com/proxy/PfhXGFnsfEbOVPojNy-qVz3EsR2wP_ocwlg4Ydw-yAnR9nRhkgkQMXc1FrN-VUSggllr5NLFJMjLZRTiAHriqcQdYlENVqzPz04s0OK3kyh_986MUPNPAfMSVpOPq74cU4TMs56vQr7CV6rVk52dQx5-HQ=w408-h726-k-no",
			c, 10, new Float(15.00), "Este comentario es para todos"
			));
			listGarage.add(Garage.create(
				"La Cochera De Grau",
			"-12.0860397,-77.0685991",
				"Avenida Grau 476, La Victoria 15033",
			"https://lh5.googleusercontent.com/p/AF1QipPVVPf6BBMiUsHDBqc_XW5nq3bs7-DR78ziOzBS=w408-h725-k-no",
			c, 10, new Float(15.00), "Este comentario es para todos"
			));
			listGarage.add(Garage.create(
				"COCHERA LUCERO",
			"-12.0860222,-77.0685993",
				"Av. Ferrocarril 30, Distrito de Lima 15011",
			"https://lh5.googleusercontent.com/p/AF1QipNW7qhub0kB5zSYePME3hfMQHTiW8kuy-7Ebu37=w408-h229-k-no",
			c, 10, new Float(15.00), "Este comentario es para todos"
			));
			listGarage.add(Garage.create(
				"C.C.La Cochera",
			"-12.0860222,-77.0685993",
				"Jir€n Huanta 653, Cercado de Lima 15001",
			"https://lh5.googleusercontent.com/p/AF1QipN05u-OXCCYjDY1AcXOalBACpogt6l6ggnjAHcD=w408-h725-k-no",
			c, 10, new Float(15.00), "Este comentario es para todos"
			));
			listGarage.add(Garage.create(
				"Cochera Carabaya",
			"-12.0726828,-77.0700284",
				"Jir€n Carabaya 684, Cercado de Lima 15001",
			"https://lh5.googleusercontent.com/p/AF1QipN1iQZHgIzOiaWKw6xlmYAYEWo--p5VPPuo3WDv=w408-h229-k-no",
			c, 10, new Float(15.00), "Este comentario es para todos"
			));
			listGarage.add(Garage.create(
				"LA COCHERA ESCUELA",
			"-12.096617,-77.0216588",
				"oficina 401, Av. Aviaci€n 2588, San Borja 15036",
			"https://geo3.ggpht.com/cbk?panoid=cJFwDD0k-_WBCJqWwVIexw&output=thumbnail&cb_client=search.TACTILE.gps&thumb=2&w=408&h=200&yaw=331.95737&pitch=0&thumbfov=100",
			c, 10, new Float(15.00), "Este comentario es para todos"
			));
			listGarage.add(Garage.create(
				"Cochera San Isidro",
			"-12.0966159,-77.0216588",
				"San Isidro 15073",
			"https://geo2.ggpht.com/cbk?panoid=GOOA30zc-Dq1phCaLKTBIw&output=thumbnail&cb_client=search.TACTILE.gps&thumb=2&w=408&h=200&yaw=310.8742&pitch=0&thumbfov=100",
			c, 10, new Float(15.00), "Este comentario es para todos"
			));
			listGarage.add(Garage.create(
				"Cochera ClÃnica Ricardo Palma",
			"-12.0966159,-77.0216588",
				"Av Pablo Carriquiry 106, San Isidro 15036",
			"https://geo1.ggpht.com/cbk?panoid=QbCRNm9F9nFErDzgjz8bjQ&output=thumbnail&cb_client=search.TACTILE.gps&thumb=2&w=408&h=200&yaw=32.037395&pitch=0&thumbfov=100",
			c, 10, new Float(15.00), "Este comentario es para todos"
			));
			listGarage.add(Garage.create(
				"Magdalena Del Mar",
			"-12.0966066,-77.0741901",
				"Jir€n Leoncio Prado 836, Cercado de Lima 15086",
			"https://geo1.ggpht.com/cbk?panoid=AVAPX6lgMvQ3osEO-WDCUw&output=thumbnail&cb_client=search.TACTILE.gps&thumb=2&w=408&h=200&yaw=201.68608&pitch=0&thumbfov=100",
			c, 10, new Float(15.00), "Este comentario es para todos"
			));
			
			return listGarage;
	}
}
