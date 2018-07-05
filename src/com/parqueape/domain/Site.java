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

import com.parqueape.infrastructure.EnumStatusSite;

@Entity
@Table(name = "TB_SITIO")
public class Site implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "garage_id", nullable = false)
	private Garage garage;

	@Column(length = 20)
	private EnumStatusSite status;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "site", cascade = CascadeType.ALL)
	private Collection<Reservation> reservations;

	public Site() {
	}

	public static Site create(Garage garage) {
		Site site = new Site();

		site.garage = garage;
		site.status = EnumStatusSite.AVAILABLE;

		return site;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Garage getGarage() {
		return garage;
	}

	public void setGarage(Garage garage) {
		this.garage = garage;
	}
	
	public EnumStatusSite getStatus() {
		return status;
	}

	public void setStatus(EnumStatusSite status) {
		this.status = status;
	}

	public Collection<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Collection<Reservation> reservations) {
		this.reservations = reservations;
	}

}
