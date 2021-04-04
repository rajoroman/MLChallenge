package com.formacionbdi.springboot.app.commons.models.dto;

import java.io.Serializable;

public class RegistroAdnDTO implements Serializable {
	
	private static final long serialVersionUID = 1285454306356845809L;
	
	private Long id;
	private String adn;
	private String type;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAdn() {
		return adn;
	}
	public void setAdn(String adn) {
		this.adn = adn;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
	
	
	
	
	
	
	



	

}
