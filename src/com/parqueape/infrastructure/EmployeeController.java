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

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;

import com.parqueape.application.CompanyService;
import com.parqueape.application.EmployeeService;
import com.parqueape.application.UserService;
import com.parqueape.domain.Company;
import com.parqueape.domain.Employee;
import com.parqueape.domain.EnumRole;
import com.parqueape.domain.User;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/employee")
public class EmployeeController {

	public static final String ROUTE_EMPLOYEE = "META-INF/../../img/employee";

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public static Response register(@FormParam("email") String email, @FormParam("password") String password,
			@FormParam("dateEntry") String init, @FormParam("salary") String payment,
			@FormParam("dateRetirement") String finish, @FormParam("turn") String turns,
			@FormParam("bankAccountNumber") String bankAccountNumber, @FormParam("names") String names,
			@FormParam("lastNames") String lastNames, @FormParam("typeDoc") EnumTypeDoc typeDoc,
			@FormParam("numDoc") String numDoc, @FormParam("photo") String photo,
			@FormParam("companyId") String companyId) {
		DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");

		Date dateEntry;
		Date dateRetirement;

		try {
			dateEntry = inputFormat.parse(init);
			dateRetirement = inputFormat.parse(finish);
		} catch (ParseException e) {
			return Response.status(400).entity(PresentationUtil.response(e.getMessage().toString(), new JSONObject()))
					.build();
		}

		Float salary = Float.parseFloat(payment);
		EnumTurn turn = EnumTurn.getRole(turns);

		User user = User.create(EnumRole.EMPLOYEE, email, password, new Date());
		Long userId = UserService.create(user);

		Company company = CompanyService.findById(Long.parseLong(companyId));

		Employee employee = Employee.create(dateEntry, salary, dateRetirement, turn, bankAccountNumber, names,
				lastNames, typeDoc, numDoc, photo, company, userId);
		Long employeeId = EmployeeService.create(employee);

		return Response.status(200).entity(PresentationUtil.response("La empleado se registro correctamente.",
				new JSONObject().append("id", employeeId))).build();
	}
	
	@PUT
	@Path("/{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public static Response update(
			@FormParam("dateEntry") String init, @FormParam("salary") String payment,
			@FormParam("dateRetirement") String finish, @FormParam("turn") String turns,
			@FormParam("bankAccountNumber") String bankAccountNumber, @FormParam("names") String names,
			@FormParam("lastNames") String lastNames, @FormParam("typeDoc") EnumTypeDoc typeDoc,
			@FormParam("numDoc") String numDoc, @FormParam("photo") String photo,
			@FormParam("companyId") String companyId, @PathParam("id") String id
	) {
		
		Employee employee = EmployeeService.findById(Long.parseLong(id));
		EmployeeService.update(employee);
		return Response.status(200).entity(PresentationUtil.response("El empleado fue actualizado correctamente.", new JSONObject().append("id", employee.getId()))).build();		
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
			System.out.println("La imagen existi�, pero se elimin�.");
			objFile.delete();
		}

		writeToFile(uploadedInputStream, uploadedFileLocation);

		JSONObject path = new JSONObject();
		path.put("path", file.getPath());
		path.put("img", fileDetail.getFileName());

		return Response.status(200).entity(PresentationUtil.response("La imagen se ha subido correctamente.", path))
				.build();

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
