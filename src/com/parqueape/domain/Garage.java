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
import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.parqueape.infrastructure.EnumStatusSite;

@Entity
@Table(name = "TB_COCHERA")
public class Garage implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 50)
	private String title;

	@Column(length = 50)
	private String coordinates;

	@Column(length = 500)
	private String address;

	@Column(length = 500)
	private String photo;

	@ManyToOne
	@JoinColumn(name = "company_id", nullable = false)
	private Company company;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "garage", cascade = CascadeType.ALL)
	private Collection<Site> sites;
	
	@Column
	private Integer numberSites;
	
	@Column
	private Float pricePerHour;
	
	@Column(length = 500)
	private String description;

	public Garage() {
	}

	public static Garage create(String title, String coordinates, String address, String photo, Company company, Integer numberSites, Float pricePerHour, String description) {
		Garage garage = new Garage();
		garage.title = title;
		garage.coordinates = coordinates;
		garage.address = address;
		garage.photo = photo;
		garage.company = company;
		garage.numberSites = numberSites;
		garage.pricePerHour = pricePerHour;
		garage.description = description;
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

	public Integer getNumberSites() {
		return numberSites;
	}

	public void setNumberSites(Integer numberSites) {
		this.numberSites = numberSites;
	}

	public Float getPricePerHour() {
		return pricePerHour;
	}

	public void setPricePerHour(Float pricePerHour) {
		this.pricePerHour = pricePerHour;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Collection<Site> getSites() {
		return sites;
	}

	public void setSites(Collection<Site> sites) {
		this.sites = sites;
	}
	
	public JSONObject getObject() {
		
		int availableSite = 0;
		int busySite = 0;
		int reservedSite = 0;
		
        try {
        	
        	JSONObject objGarage = new JSONObject();
    		objGarage.put("id", getId());
    		objGarage.put("title", getTitle());
    		objGarage.put("coordinates", getCoordinates());
    		objGarage.put("address", getAddress());
    		objGarage.put("photo", getPhoto());
    		objGarage.put("numberSites", getNumberSites());
    		objGarage.put("pricePerHour", getPricePerHour());
    		objGarage.put("description", getDescription());

    		if (getSites().size() > 0) {

    			JSONArray arrSite = new JSONArray();

    			for (Site site : getSites()) {
    				JSONObject objSite = new JSONObject();
    				objSite.put("id", site.getId());
    				objSite.put("status", site.getStatus());

    				arrSite.put(objSite);
    				
    				if(site.getStatus().equals(EnumStatusSite.AVAILABLE.toString())) {
    					availableSite++;
    				}
    				
    				if(site.getStatus().equals(EnumStatusSite.BUSY.toString())) {
    					busySite++;
    				}
    				
    				if(site.getStatus().equals(EnumStatusSite.RESERVED.toString())) {
    					reservedSite++;
    				}
    			}
    			
    			objGarage.put("availableSite", availableSite);
        		objGarage.put("busySite", busySite);
        		objGarage.put("reservedSite", reservedSite);

    			objGarage.put("sites", arrSite);
    		}
            
            return objGarage;
        } catch (JSONException e) {
                return null;
        }
}
}
