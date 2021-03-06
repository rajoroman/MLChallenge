package com.formacionbdi.springboot.app.adn.service;

import java.util.List;

import com.formacionbdi.springboot.app.commons.models.dto.StatsDTO;
import com.formacionbdi.springboot.app.commons.models.entity.RegistroAdn;

public interface AdnService {
	public List<RegistroAdn> findAll();
	public RegistroAdn save(RegistroAdn producto);
	public String isMutante(String[] adnIn);
	public StatsDTO stats();
}
