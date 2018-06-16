package com.parqueape.domain;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
@Table(name = "TB_COCHERA")
@XmlRootElement
public class Garage implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@XmlElement(name = "id")
	private Long id;

	@Column(length = 50)
	@XmlElement(name = "title")
	private String title;

	@Column(length = 50)
	@XmlElement(name = "coordinates")
	private String coordinates;

	@Column(length = 500)
	@XmlElement(name = "address")
	private String address;

	@Column(length = 500)
	@XmlElement(name = "photo")
	private String photo;

	@ManyToOne
	@JoinColumn(name = "company_id", nullable = false)
	private Company company;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "garage", cascade = CascadeType.ALL)
	private Collection<Site> sites;

	public Garage() {
	}

	public static Garage create(String title, String coordinates, String address, String photo, Company company) {
		Garage garage = new Garage();
		garage.title = title;
		garage.coordinates = coordinates;
		garage.address = address;
		garage.photo = photo;
		garage.company = company;
		return garage;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	
	
	public Collection<Site> getSites() {
		return sites;
	}

	public void setSites(Collection<Site> sites) {
		this.sites = sites;
	}

	public JSONObject getObject() {
        try {
        	
        	JSONObject obj = new JSONObject();
        	
        	obj.put("id", id);
			obj.put("title", title);
			obj.put("coordinates", coordinates);
			obj.put("address", address);
			obj.put("photo", photo);
			obj.put("company", company);
			obj.put("sites", sites);
            
            return new JSONObject().put("garage", obj);
        } catch (JSONException e) {
                return null;
        }
}
}
