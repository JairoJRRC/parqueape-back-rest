package com.parqueape.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_PARQUEO_ASIGNADO")
public class ParkingAssignment implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Long employeeId;
	
	@Column
	private Long garageId;

	public ParkingAssignment() {
	}

	public static ParkingAssignment create(Long employeeId, Long garageId) {
		ParkingAssignment obj = new ParkingAssignment();
		
		obj.employeeId = employeeId;
		obj.garageId = garageId;
		
		return obj;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public Long getGarageId() {
		return garageId;
	}

	public void setGarageId(Long garageId) {
		this.garageId = garageId;
	}
	
	

}
