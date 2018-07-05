package com.parqueape.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Type;
import org.json.JSONException;
import org.json.JSONObject;

import com.parqueape.infrastructure.EnumState;
import com.parqueape.infrastructure.EnumTurn;
import com.parqueape.infrastructure.EnumTypeDoc;

@Entity
@Table(name = "TB_EMPLEADO")
@XmlRootElement
public class Employee implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	@Temporal(TemporalType.DATE)
	private Date dateEntry;

	@Column(precision = 2)
	private Float salary;

	@Column
	@Temporal(TemporalType.DATE)
	private Date dateRetirement;

	@Column
	@Enumerated(EnumType.STRING)
	private EnumState state;

	@Column
	@Enumerated(EnumType.STRING)
	private EnumTurn turn;

	@Column
	private String bankAccountNumber;

	@Column(length = 50)
	private String names;

	@Column(length = 50)
	private String lastNames;

	@Column
	@Enumerated(EnumType.STRING)
	private EnumTypeDoc typeDoc;

	@Column(length = 20)
	private String numDoc;

	@Column
	@Type(type="text")
	private String photo;

	@ManyToOne
	@JoinColumn(name = "company_id", nullable = false)
	private Company company;
	
	@Column
	private Long userId;

	public Employee() {
	}

	public static Employee create(Date dateEntry, Float salary, Date dateRetirement, EnumTurn turn,
			String bankAccountNumber, String names, String lastNames, EnumTypeDoc typeDoc, String numDoc, String photo,
			Company company, Long userId) {
		Employee employee = new Employee();
		employee.dateEntry = dateEntry;
		employee.salary = salary;
		employee.dateRetirement = dateRetirement;
		employee.state = EnumState.ACTIVE;
		employee.turn = turn;
		employee.bankAccountNumber = bankAccountNumber;
		employee.names = names;
		employee.lastNames = lastNames;
		employee.typeDoc = typeDoc;
		employee.numDoc = numDoc;
		employee.photo = photo;
		employee.company = company;
		employee.userId = userId;
		return employee;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateEntry() {
		return dateEntry;
	}

	public void setDateEntry(Date dateEntry) {
		this.dateEntry = dateEntry;
	}

	public Float getSalary() {
		return salary;
	}

	public void setSalary(Float salary) {
		this.salary = salary;
	}

	public Date getDateRetirement() {
		return dateRetirement;
	}

	public void setDateRetirement(Date dateRetirement) {
		this.dateRetirement = dateRetirement;
	}

	public EnumState getState() {
		return state;
	}

	public void setState(EnumState state) {
		this.state = state;
	}

	public EnumTurn getTurn() {
		return turn;
	}

	public void setTurn(EnumTurn turn) {
		this.turn = turn;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getLastNames() {
		return lastNames;
	}

	public void setLastNames(String lastNames) {
		this.lastNames = lastNames;
	}

	public EnumTypeDoc getTypeDoc() {
		return typeDoc;
	}

	public void setTypeDoc(EnumTypeDoc typeDoc) {
		this.typeDoc = typeDoc;
	}

	public String getNumDoc() {
		return numDoc;
	}

	public void setNumDoc(String numDoc) {
		this.numDoc = numDoc;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public JSONObject getObject() {
		try {

			JSONObject obj = new JSONObject();
			obj.put("id", getId());
			obj.put("dateEntry", getDateEntry());
			obj.put("salary", getSalary());
			obj.put("dateRetirement", getDateRetirement());
			obj.put("state", getState());
			obj.put("turn", getTurn());
			obj.put("bankAccountNumber", getBankAccountNumber());
			obj.put("names", getNames());
			obj.put("lastNames", getLastNames());
			obj.put("typeDoc", getTypeDoc());
			obj.put("numDoc", getNumDoc());
			obj.put("photo", getPhoto());
			obj.put("companyId", getCompany().getId());
			obj.put("userId", getUserId());

			return new JSONObject().put("employee", obj);
		} catch (JSONException e) {
			return null;
		}
	}
	
	public JSONObject getObjectList() {
		try {

			JSONObject obj = new JSONObject();
			obj.put("id", getId());
			obj.put("dateEntry", getDateEntry());
			obj.put("salary", getSalary());
			obj.put("dateRetirement", getDateRetirement());
			obj.put("state", getState());
			obj.put("turn", getTurn());
			obj.put("bankAccountNumber", getBankAccountNumber());
			obj.put("names", getNames());
			obj.put("lastNames", getLastNames());
			obj.put("typeDoc", getTypeDoc());
			obj.put("numDoc", getNumDoc());
			obj.put("photo", getPhoto());
			obj.put("companyId", getCompany().getId());
			obj.put("userId", getUserId());

			return obj;
		} catch (JSONException e) {
			return null;
		}
	}
}
