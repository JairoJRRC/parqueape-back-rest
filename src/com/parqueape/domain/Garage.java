package com.parqueape.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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

	@Column(length = 50)
	@XmlElement(name = "address")
	private String address;

	@Column(length = 50)
	@XmlElement(name = "photo")
	private String photo;

	@ManyToOne
	@JoinColumn(name = "company_id", nullable = false)
	private Company company;

	@OneToMany(mappedBy = "garage")
	private Set<Site> sites;

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

	public Set<Site> getSites() {
		return sites;
	}

	public void setSites(Set<Site> sites) {
		this.sites = sites;
	}
}
