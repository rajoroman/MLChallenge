package com.formacionbdi.springboot.app.adn.implement;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import com.formacionbdi.springboot.app.adn.dao.AdnDao;
import com.formacionbdi.springboot.app.adn.service.AdnService;
import com.formacionbdi.springboot.app.commons.models.dto.AdnDTO;
import com.formacionbdi.springboot.app.commons.models.dto.RegistroAdnDTO;
import com.formacionbdi.springboot.app.commons.models.dto.StatsDTO;
import com.formacionbdi.springboot.app.commons.models.entity.RegistroAdn;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/test-adn-services.xml"})
public class AdnServiceImplTest {
	
	
	@Autowired
	AdnService adnService;
	
	@Autowired
	AdnDao adnDao;
	
	@Test
	public void testIsMutanteRegistered() {
		RegistroAdn registroDao = new RegistroAdn();
		String[] adn = {"ATGCGA","CTTTGC","TTATGT","AGATTG","CACCTA","TCACTG"};
		String result = "";
		EasyMock.reset(adnDao);
		EasyMock.expect(adnDao.findRegistroAdnByAdn(adn)).andReturn(registroDao);
		EasyMock.replay(adnDao);
		result = adnService.isMutante(adn);
		assertEquals(result,"DNA is previously registered");
	}
	
	@Test
	public void testCharacterInvalid() {
		RegistroAdn registroDao = new RegistroAdn();
		String[] adn = {"ATPCGA","CTTTGC","TTATGT","AGATTG","CACCTA","TCACTG"};
		String result = "";
		EasyMock.reset(adnDao);
		EasyMock.expect(adnDao.findRegistroAdnByAdn(adn)).andReturn(registroDao);
		EasyMock.replay(adnDao);
		result = adnService.isMutante(adn);
	}
	
	@Test
	public void testIsMutanteNoExist() {
		RegistroAdn registroDao = new RegistroAdn();
		String[] adn = {"TTGC","CTCT","ACTG","CCAT"};
		String result = "";
		registroDao.setAdn(adn);
		registroDao.setId(0L);
		registroDao.setType("M");
		EasyMock.reset(adnDao);
		EasyMock.expect(adnDao.findRegistroAdnByAdn(adn)).andReturn(EasyMock.anyObject(RegistroAdn.class));
		EasyMock.expect(adnDao.save(registroDao)).andReturn(registroDao);
		EasyMock.expect(adnDao.save(registroDao)).andReturn(registroDao);
		EasyMock.replay(adnDao);
		result = adnService.isMutante(adn);
	}
	
	@Test
	public void testIsMutante() {
		RegistroAdn registroDao = new RegistroAdn();
		String[] adn = {"TTGC","CTCT","ACTG","CCAT"};
		String result = "";
		registroDao.setAdn(adn);
		registroDao.setId(0L);
		registroDao.setType("M");
		EasyMock.reset(adnDao);
		EasyMock.expect(adnDao.findRegistroAdnByAdn(adn)).andReturn(EasyMock.anyObject(RegistroAdn.class));
		EasyMock.expect(adnDao.save(registroDao)).andReturn(registroDao);
		EasyMock.expect(adnDao.save(registroDao)).andReturn(registroDao);
		EasyMock.replay(adnDao);
		result = adnService.isMutante(adn);
		assertEquals(result, "M");
	}
	
	@Test
	public void testIsHuman() {
		RegistroAdn registroDao = new RegistroAdn();
		String[] adn = {"ATGCGAATA","CTGTCCCAC","TTAAGTACT","AGAAAGACA","CGCCTATTC","TCACTACCT","ACTGTGTAA","ACACCAGTC","CGTAACATG"};
		String result = "";
		EasyMock.reset(adnDao);
		EasyMock.expect(adnDao.findRegistroAdnByAdn(adn)).andReturn(EasyMock.anyObject(RegistroAdn.class));
		EasyMock.expect(adnDao.save(registroDao)).andReturn(registroDao);
		EasyMock.expect(adnDao.save(registroDao)).andReturn(registroDao);
		EasyMock.replay(adnDao);
		result = adnService.isMutante(adn);
		assertEquals(result, "H");
	}
	
	@Test
	public void testIsMutanteStructureInvalid() {
		RegistroAdn registroDao = new RegistroAdn();
		String[] adn = {"ATGA","CTTTGC","TTATGT","AGATTG","CACCTA","TCACTG"};
		String result = "";
		registroDao.setAdn(adn);
		registroDao.setId(0L);
		registroDao.setType("M");
		EasyMock.reset(adnDao);
		EasyMock.expect(adnDao.findRegistroAdnByAdn(adn)).andReturn(EasyMock.anyObject(RegistroAdn.class));
		EasyMock.expect(adnDao.save(registroDao)).andReturn(registroDao);
		EasyMock.expect(adnDao.save(registroDao)).andReturn(registroDao);
		EasyMock.replay(adnDao);
		result = adnService.isMutante(adn);
	}
	
	@Test
	public void testIsMutanteObliquies() {
		RegistroAdn registroDao = new RegistroAdn();
		String[] adn = {"ATGCGAAT","CTGTACCA","TTAAGTAC","AGAAAGAC","CGCCTATT","TCACTTCC","ACCGTGTA","ACACCAGA"};
		String result = "";
		registroDao.setAdn(adn);
		registroDao.setId(0L);
		registroDao.setType("M");
		EasyMock.reset(adnDao);
		EasyMock.expect(adnDao.findRegistroAdnByAdn(adn)).andReturn(EasyMock.anyObject(RegistroAdn.class));
		EasyMock.expect(adnDao.save(registroDao)).andReturn(registroDao);
		EasyMock.expect(adnDao.save(registroDao)).andReturn(registroDao);
		EasyMock.replay(adnDao);
		result = adnService.isMutante(adn);
		
	}
	
	
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
	
	
	@Test
	public void testFindAll() {
		RegistroAdn registroAdn = new RegistroAdn();
		List<RegistroAdn> list = new ArrayList<RegistroAdn>();
		String[] adn = {"TTGC","CTCT","ACTG","CCAT"};
		registroAdn.setAdn(adn);
		registroAdn.setId(0L);
		registroAdn.setType("M");
		list.add(registroAdn);
		EasyMock.reset(adnDao);
		EasyMock.expect(adnDao.findAll()).andReturn(list).times(1);
		EasyMock.replay(adnDao);
		List<RegistroAdn> response = (List<RegistroAdn>) adnDao.findAll();
		EasyMock.verify(adnDao);
	}
	
	
	@Test
	public void testStats() {
		int val = 100;
		StatsDTO statsDTO = new StatsDTO();
		EasyMock.reset(adnDao);
		EasyMock.expect(adnDao.countAll()).andReturn(val).times(1);
		EasyMock.expect(adnDao.countByType("H")).andReturn(val).times(1);
		EasyMock.expect(adnDao.countByType("M")).andReturn(val).times(1);
		EasyMock.replay(adnDao);
		statsDTO =  adnService.stats();
		EasyMock.verify(adnDao);
	}
	
	
	
	
	
	
	
	
    

	

}
