package com.formacionbdi.springboot.app.commons.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="registroadn")
public class RegistroAdn implements Serializable {
	
	private static final long serialVersionUID = 1285454306356845809L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String[] adn;
	private String type;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String[] getAdn() {
		return adn;
	}
	public void setAdn(String[] adn) {
		this.adn = adn;
	}
	
	
	
	
	
	
	
	
	
	
	
	



	

}
