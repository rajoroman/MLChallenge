package com.formacionbdi.springboot.app.adn.controllers;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.formacionbdi.springboot.app.adn.service.AdnService;
import com.formacionbdi.springboot.app.commons.models.dto.AdnDTO;
import com.formacionbdi.springboot.app.commons.models.dto.StatsDTO;
import com.formacionbdi.springboot.app.commons.models.entity.RegistroAdn;
/*Controller to expose services /list, /mutant, /stats*/
@RestController
public class AdnController {
	
	final  Logger log = LoggerFactory.getLogger(AdnController.class);

	@Autowired
	private AdnService adnService;
	
	/*Controller to get the list of registered DNAs. Returns a JSON with the data of each record*/
	@GetMapping("/list")
	public List<RegistroAdn> listar(){
		return adnService.findAll().stream().map(adnAux -> {
			return adnAux;
		}).collect(Collectors.toList());
	}
	
	/*Controller to send a DNA strand and determine if it is mutant or not
	Controller to send a DNA strand and determine if it is mutant or not. 
	If it is mutant it returns code 200, if it is not a mutant it returns code 403 FORBIDDEN. 
	If there are structure errors, it returns JSON with the error.*/
	@PostMapping("/mutant")
	public ResponseEntity<?> isMutant(@RequestBody AdnDTO adnIn) {
		HttpStatus status = HttpStatus.FORBIDDEN;
		Map<String, String> json = new HashMap<>();
		String result = adnService.isMutante(adnIn.getDna());
		if(!result.isBlank() && ( result.equalsIgnoreCase("M") || result.equalsIgnoreCase("H"))) {
			if(result.equalsIgnoreCase("M")) {
			   json.put("RESULT","true");
			   status = HttpStatus.OK;
			}
			else {
				json.put("RESULT","false");
				status = HttpStatus.FORBIDDEN;
			}
		}
		else {
			json.put("ERROR",result);
			json.put("CODE:",HttpStatus.FORBIDDEN.toString());
			status = HttpStatus.FORBIDDEN;
		}
		
		return new ResponseEntity<Map<String, String>>(json,status);
	}
	/*Controller to get the statistics*/
	@GetMapping("/stats")
	public  ResponseEntity<?> stats() {
		HttpStatus status = HttpStatus.OK;
		Map<String, StatsDTO> json = new HashMap<>();
		StatsDTO result = adnService.stats();
		json.put("ADN", result);
		return  new ResponseEntity<Map<String, StatsDTO>>(json,status);	
	}
	
}	
