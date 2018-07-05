package com.parqueape.infrastructure;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import com.parqueape.application.CompanyService;
import com.parqueape.application.EmployeeService;
import com.parqueape.application.ParkingAssignmentService;
import com.parqueape.application.UserService;
import com.parqueape.domain.Company;
import com.parqueape.domain.Employee;
import com.parqueape.domain.EnumRole;
import com.parqueape.domain.Garage;
import com.parqueape.domain.ParkingAssignment;
import com.parqueape.domain.User;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/employee")
public class EmployeeController {

	public static final String ROUTE_EMPLOYEE = "META-INF/../../img/employee";

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public static Response register(
			@FormParam("email") String email,
			@FormParam("password") String password,
			@FormParam("dateEntry") String init,
			@FormParam("salary") String payment,
			@FormParam("dateRetirement") String finish,
			@FormParam("turn") String turns,
			@FormParam("bankAccountNumber") String bankAccountNumber,
			@FormParam("names") String names,
			@FormParam("lastNames") String lastNames,
			@FormParam("typeDoc") String typeDoc,
			@FormParam("numDoc") String numDoc,
			@FormParam("photo") String photo,
			@FormParam("companyId") String companyId,
			@FormParam("garageId") String garage_id
	) {
		try {
			
			UserService.validateExistUser(email, EnumRole.EMPLOYEE);
		
			DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date dateEntry;
			Date dateRetirement;
			Long garageId;
			Float salary;
			EnumTurn turn;
			
			dateEntry = inputFormat.parse(init);
			dateRetirement = inputFormat.parse(finish);
			
			garageId = Long.parseLong(garage_id);
			salary = Float.parseFloat(payment);
			turn = EnumTurn.getRole(turns);

			User user = User.create(EnumRole.EMPLOYEE, email, password, new Date());
			Long userId = UserService.create(user);

			Company company = CompanyService.findById(Long.parseLong(companyId));

			Employee employee = Employee.create(dateEntry, salary, dateRetirement, turn, bankAccountNumber, names,
					lastNames, EnumTypeDoc.getRole(typeDoc), numDoc, photo, company, userId);
			Long employeeId = EmployeeService.create(employee);

			ParkingAssignment parkingAssignment = ParkingAssignment.create(employeeId, garageId);
			ParkingAssignmentService.create(parkingAssignment);

			return Response.status(200)
					.entity(PresentationUtil.response("La empleado se registro correctamente.",
							new JSONObject().append("id", employeeId)))
					.header("Access-Control-Allow-Origin", "*").build();
		} catch (Exception e) {
			return Response.status(400).entity(PresentationUtil.error(e.getMessage())).build();
		}
		
	}
	
	@PUT
	@Path("/{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public static Response update(
			@FormParam("dateEntry") String init,
			@FormParam("salary") String payment,
			@FormParam("dateRetirement") String finish,
			@FormParam("turn") String turns,
			@FormParam("bankAccountNumber") String bankAccountNumber,
			@FormParam("names") String names,
			@FormParam("lastNames") String lastNames,
			@FormParam("typeDoc") String typeDoc,
			@FormParam("numDoc") String numDoc,
			@FormParam("photo") String photo,
			@PathParam("id") String id
	) {
		try {
			DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");

			Date dateEntry;
			Date dateRetirement;

			dateEntry = inputFormat.parse(init);
			dateRetirement = inputFormat.parse(finish);

			Employee employee = EmployeeService.findById(Long.parseLong(id));
			employee.setDateEntry(dateEntry);
			employee.setSalary(Float.parseFloat(payment));
			employee.setDateRetirement(dateRetirement);
			employee.setTurn(EnumTurn.getRole(turns));
			employee.setBankAccountNumber(bankAccountNumber);
			employee.setNames(names);
			employee.setLastNames(lastNames);
			employee.setTypeDoc(EnumTypeDoc.getRole(typeDoc));
			employee.setNumDoc(numDoc);
			employee.setPhoto(photo);

			EmployeeService.update(employee);

			return Response.status(200)
					.entity(PresentationUtil.response("El empleado fue actualizado correctamente.",
							new JSONObject().append("id", employee.getId())))
					.header("Access-Control-Allow-Origin", "*").build();
		} catch (Exception e) {
			return Response.status(400).entity(PresentationUtil.error(e.getMessage())).build();
		}

	}

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(ROUTE_EMPLOYEE).getFile());
		String uploadedFileLocation = file.getPath() + "\\" + fileDetail.getFileName();

		File objFile = new File(uploadedFileLocation);

		if (objFile.exists()) {
			System.out.println("La imagen existió, pero se eliminó.");
			objFile.delete();
		}

		writeToFile(uploadedInputStream, uploadedFileLocation);

		JSONObject path = new JSONObject();
		path.put("path", file.getPath());
		path.put("img", fileDetail.getFileName());

		return Response.status(200).entity(PresentationUtil.response("La imagen se ha subido correctamente.", path))
				.header("Access-Control-Allow-Origin", "*").build();

	}
	
	@GET
	@Path("/company/{companyId}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public static Response getEmployeesByCompanyId(
			@PathParam("companyId") String companyId
	) {
		List<Employee> employees = EmployeeService.getEmployeesByCompany(Long.parseLong(companyId));

		JSONArray arrEmployee = new JSONArray();
		if (employees.size() > 0) {
			for (Employee employee : employees) {
				arrEmployee.put(employee.getObjectList());
			}
		}

		return Response.status(200)
				.entity(PresentationUtil.response("Se obtuvo correctamente los empleados.",
						new JSONObject().put("employees", arrEmployee)))
				.header("Access-Control-Allow-Origin", "*").build();
	}
	
	@GET
	@Path("/{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public static Response get(@PathParam("id") String id) {
		Employee employe = EmployeeService.findById(Long.parseLong(id));
		JSONObject result = employe.getObject();

		return Response.status(200).entity(PresentationUtil.response("Se obtuvo correctamente al empleado.", result))
				.header("Access-Control-Allow-Origin", "*").build();
	}

	private void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) {

		try {
			OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
