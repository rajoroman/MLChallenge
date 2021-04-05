package com.formacionbdi.springboot.app.adn.dto;


import org.junit.Test;
import org.junit.runner.RunWith;

import com.formacionbdi.springboot.app.commons.models.dto.AdnDTO;
import com.formacionbdi.springboot.app.commons.models.dto.RegistroAdnDTO;
import com.formacionbdi.springboot.app.commons.models.dto.StatsDTO;
import com.formacionbdi.springboot.app.commons.models.entity.RegistroAdn;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/test-adn-services.xml"})
public class AdnDTOTest {
	
	
	@Test
	public void testRegistroDTO() {
		RegistroAdnDTO registroAdnDTO = new RegistroAdnDTO();
		registroAdnDTO.setAdn("ATGCGAAT");
		registroAdnDTO.setId(0L);
		registroAdnDTO.setType("H");
		registroAdnDTO.getAdn();
		registroAdnDTO.getId();
		registroAdnDTO.getType();
	}
	
	@Test
	public void testStatsDTO() {
		StatsDTO statsDTO = new StatsDTO();
		statsDTO.setCount_human_dna(10);
		statsDTO.setCount_mutant_dna(25);
		statsDTO.setRatio(9.999);
		statsDTO.getCount_human_dna();
		statsDTO.getCount_mutant_dna();
		statsDTO.getRatio();
	}
	
	@Test
	public void testAdnDTO() {
		AdnDTO adnDTO = new AdnDTO();
		String[] adnArray = {"ATGCGAATA","CTGTCCCAC","TTAAGTACT","AGAAAGACA","CGCCTATTC","TCACTACCT","ACTGTGTAA","ACACCAGTC","CGTAACATG"};
		adnDTO.setDna(adnArray);
		adnDTO.getDna();
		
	}
	
	
	@Test
	public void testRegistroAdnEntity() {
		RegistroAdn registroAdn = new RegistroAdn();
		String[] adnArray = {"ATGCGAATA","CTGTCCCAC","TTAAGTACT","AGAAAGACA","CGCCTATTC","TCACTACCT","ACTGTGTAA","ACACCAGTC","CGTAACATG"};
		registroAdn.setAdn(adnArray);
		registroAdn.setId(0L);
		registroAdn.setType("M");
		registroAdn.getAdn();
		registroAdn.getType();
		registroAdn.getId();
	}
	
	
	
	
	
	
	
	
    

	

}
