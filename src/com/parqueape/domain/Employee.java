package com.parqueape.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.parqueape.infrastructure.EnumState;
import com.parqueape.infrastructure.EnumTurn;
import com.parqueape.infrastructure.EnumTypeDoc;

@Entity
@Table(name = "TB_EMPLEADO")
@XmlRootElement
public class Employee implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@XmlElement(name = "id")
	private Long id;

	@Column
	@Temporal(TemporalType.DATE)
	@XmlElement(name = "dateEntry")
	private Date dateEntry;

	@Column(precision = 2)
	@XmlElement(name = "salary")
	private Float salary;

	@Column
	@Temporal(TemporalType.DATE)
	@XmlElement(name = "dateRetirement")
	private Date dateRetirement;

	@Column
	@XmlElement(name = "state")
	private EnumState state;

	@Column
	@XmlElement(name = "turn")
	private EnumTurn turn;

	@Column
	@XmlElement(name = "bankAccountNumb")
	private String bankAccountNumber;

	@Column(length = 50)
	@XmlElement(name = "names")
	private String names;

	@Column(length = 50)
	@XmlElement(name = "lastNames")
	private String lastNames;

	@Column
	@XmlElement(name = "typeDoc")
	private EnumTypeDoc typeDoc;

	@Column(length = 20)
	@XmlElement(name = "numDoc")
	private String numDoc;

	@Column(length = 50)
	@XmlElement(name = "photo")
	private String photo;

	@ManyToOne
	@JoinColumn(name = "company_id", nullable = false)
	private Company company;

	public Employee() {
	}

	public static Employee create(Date dateEntry, Float salary, Date dateRetirement, EnumTurn turn,
			String bankAccountNumber, String names, String lastNames, EnumTypeDoc typeDoc, String numDoc, String photo,
			Company company) {
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
}
