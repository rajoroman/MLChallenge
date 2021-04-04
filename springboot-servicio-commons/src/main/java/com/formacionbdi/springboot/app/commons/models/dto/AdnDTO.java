package com.formacionbdi.springboot.app.commons.models.dto;

import java.io.Serializable;

public class AdnDTO implements Serializable {
	private static final long serialVersionUID = 1285454306356845809L;

	private String[] dna;
	
	public String[] getDna() {
		return dna;
	}
	public void setDna(String[] dna) {
		this.dna = dna;
	}

}
