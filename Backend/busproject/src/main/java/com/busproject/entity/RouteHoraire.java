package com.busproject.entity;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "route_horaire")
public class RouteHoraire {
	
	@Id
	@GeneratedValue
	@Column(name="horaire_id")
	private int id;
	
	private LocalTime horaire;

	public RouteHoraire(int id, LocalTime horaire) {
		super();
		this.id = id;
		this.horaire = horaire;
	}
	
	public RouteHoraire() {
		super();
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalTime gethoraire() {
		return horaire;
	}

	public void setHoraire(LocalTime horaire) {
		this.horaire = horaire;
	}
	
	

}
