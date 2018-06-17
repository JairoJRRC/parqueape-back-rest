package com.parqueape.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
@Table(name = "TB_EMPRESA")
@XmlRootElement
public class Company implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@XmlElement(name = "id")
	private Long id;
	
	@Column(length=11)
	@XmlElement(name = "ruc")
	private Integer ruc;
	@Column(length=50)
	@XmlElement(name = "businessName")
	private String businessName;
	
	@Column(length=20)
	@XmlElement(name = "phoneNumber")
	private String phoneNumber;
	
	@Column(length=50)
	@XmlElement(name = "tradeName")
	private String tradeName;
	
	@Column
	@XmlElement(name = "userId")
	private Long userId;
	
	@OneToMany(mappedBy = "company")
	private Set<Garage> garages;
	
	@OneToMany(mappedBy = "company")
	private Set<Employee> employees;

	public Company() {
	}

	public static Company create(Integer ruc, String phoneNumber, String tradeName, Long userId) {
		Company obj = new Company();

		obj.ruc = ruc;
		obj.phoneNumber = phoneNumber;
		obj.tradeName = tradeName;
		obj.userId = userId;
		
		return obj;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getRuc() {
		return ruc;
	}

	public void setRuc(Integer ruc) {
		this.ruc = ruc;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
    public Set<Garage> getGarages() {
		return garages;
	}

	public void setGarages(Set<Garage> garages) {
		this.garages = garages;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public JSONObject getObject() {
            try {
            	
            	JSONObject obj = new JSONObject();
            	
                obj.put("id", id);
                obj.put("ruc", ruc);
				obj.put("businessName", businessName);
				obj.put("phoneNumber", phoneNumber);
				obj.put("tradeName", tradeName);
				obj.put("userId", userId);
                
                return new JSONObject().put("company", obj);
            } catch (JSONException e) {
                    return null;
            }
    }
	
	
}
