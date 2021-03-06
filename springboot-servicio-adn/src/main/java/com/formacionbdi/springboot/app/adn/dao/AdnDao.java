package com.formacionbdi.springboot.app.adn.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.formacionbdi.springboot.app.commons.models.entity.RegistroAdn;

public interface AdnDao extends CrudRepository<RegistroAdn, Long> {
	
	public RegistroAdn findRegistroAdnByAdn(String[] adn);
	
	@Query("select count(r) from RegistroAdn r")
	public int countAll();
	
	@Query("select count(r) from RegistroAdn r where r.type = ?1 ")
	public int countByType(String type);

}
