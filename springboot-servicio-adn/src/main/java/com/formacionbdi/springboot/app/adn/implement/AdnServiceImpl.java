package com.formacionbdi.springboot.app.adn.implement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.formacionbdi.springboot.app.adn.dao.AdnDao;
import com.formacionbdi.springboot.app.adn.service.AdnService;
import com.formacionbdi.springboot.app.commons.models.dto.StatsDTO;
import com.formacionbdi.springboot.app.commons.models.entity.RegistroAdn;

@Service
public class AdnServiceImpl implements AdnService {
	
	final  Logger log = LoggerFactory.getLogger(AdnServiceImpl.class);
	private static final RegistroAdn NULL = null;
	final int minLength = 4;
	int occurrences = 0;
	Map<Integer, Integer> position = new HashMap<Integer, Integer>(); 

	@Autowired
	private AdnDao adnDao;
	
    /*Obtains the list of all DNAs stored in the database*/
	@Override
	@Transactional(readOnly = true)
	public List<RegistroAdn> findAll() {
		return (List<RegistroAdn>) adnDao.findAll();
	}
	
    /*Save the DNA in the database*/
	@Override
	@Transactional
	public RegistroAdn save(RegistroAdn registroAdn) {
		return adnDao.save(registroAdn);
	}
	
    /*Determine if DNA exists in the database*/
	private boolean searchAdn(String[] adnIn) {
		RegistroAdn registroAdnEntity = new RegistroAdn();
        registroAdnEntity = adnDao.findRegistroAdnByAdn(adnIn);
		if(registroAdnEntity == NULL) 	    
		   return false;
		return true;
	}
	
	/*Get the number of humans and mutants registered, calculate the ratio*/
	public StatsDTO stats() {
		float ratio = 0;
		StatsDTO statsDTO = new StatsDTO();
		int countAll =  adnDao.countAll();
		int countHuman = adnDao.countByType("H");
		int countMutant = adnDao.countByType("M");
		statsDTO.setCount_human_dna(countHuman);
		statsDTO.setCount_mutant_dna(countMutant);
		ratio = countAll > 0 ? (float)countMutant/countAll : 0;
		statsDTO.setRatio(ratio);
		log.info(String.format("Ratio: %s", ratio));
		return statsDTO;
	}
	
	/*Search for sequences within a string*/
	private int searchSequence(String stringIn) {
		String[] sequences = {"AAAA", "TTTT", "CCCC", "GGGG"};
		int times = 0;
		for(String sequece : sequences) {
			if( stringIn.contains(sequece) )
				times++;
		}
		log.info(String.format("Result search sequence: %s", times));
		return times;
	}
	
	/*This method determines if it is human or mutant*/
	/*In the variable result, it returns H if it is human, */
	/*M if it is mutant. If a structure error occurs, */
	/*it is returned in the form of a string*/
	public String isMutante(String[] adnIn) {
		final int minOccurrences = 2;
		final String human = "H";
		final String mutant = "M";
		String result = "";
		RegistroAdn registroAdnEntity = new RegistroAdn();
		if(searchAdn(adnIn)) {
			return result="DNA is previously registered";
		}
		result = validDimension(adnIn);
		if(result.equalsIgnoreCase("OK"))
			result = validCharacters(adnIn);
		if(result.equalsIgnoreCase("OK")) {
			occurrences = horizontal(adnIn);
			if(occurrences < minOccurrences) {
				occurrences += vertical(adnIn);
				if(occurrences < minOccurrences) 
					occurrences += oblique(adnIn);
				if(occurrences < minOccurrences) 
					occurrences += obliqueInv(adnIn);
			}
			result = occurrences >= minOccurrences ? mutant : human;
			registroAdnEntity.setType(result);
			registroAdnEntity.setAdn(adnIn);
			save(registroAdnEntity);
			log.info(String.format("Result occurrences: %s", occurrences));
			log.info(String.format("Result Error: %s", result));
		}
		
		return result;
	}
	
	/*It is validated that the DNA has valid characters*/
	private String validCharacters(String[] adnIn) {
			final String characterValid = "ATCG";
			final String error = "Character Invalid";
			log.info(String.format("Validating characters"));
			for (String cadena : adnIn){
				for(Character character : cadena.toCharArray()) {
					if( characterValid.indexOf(character) < 0) {
						return error;
					}
				}
		}
			
		return "OK";
	}
	
	/*It is validated that each element of the array has the same number of characters*/
	private String validDimension(String[] adnIn) {
		final String error = "DNA structure error length difference";
		log.info(String.format("Validating structure"));
		for (String cadena : adnIn){
			if(cadena.length()!= adnIn.length || cadena.length() < minLength) {
				log.info(error);
				return error;
			}
		}
		
		return "OK";
	}
	/*Determine if horizontal sequences exist*/
	private int horizontal(String[] adnIn) {
		int  times = 0;
		for (String cadena : adnIn){
			times += searchSequence(cadena);
		}
		return times;
	}
	/*Determine if there are vertical sequences*/
	private int vertical(String[] adnIn) {
		int times = 0;
		int elementLength = adnIn[0].length();
		for(int i = 0; i < elementLength; i++ ) {
			String vertical = "";
			for (String cadena : adnIn){
				vertical += cadena.charAt(i);
			}
			times += searchSequence(vertical);
		}
		
		return times;
	}
	/*Determine the main oblique and the positions of its characters*/
	private String obliquePrincipal(String[] adnIn) {
		String obliqueString = "";
		position.clear();
		for(int i = 0; i < adnIn[0].length(); i++ ) {
			obliqueString += adnIn[i].charAt(i);
			position.put(i,i);
		}
		log.info("Oblique Prinicpal: %s",obliqueString);		
		return obliqueString;
	}
	/*Determines the secondary or inverse oblique and the positions of its characters*/
	private String obliqueInverse(String[] adnIn) {
		String obliqueString = "";
		int elementLength = adnIn[0].length() - 1;
		int posH = 0;
		position.clear();
		for(int i = elementLength; i >= 0 ; i--) {
			position.put(posH,i);
			obliqueString += adnIn[posH++].charAt(i);
		}
		log.info("Oblique Inverse: %s",obliqueString);
		return obliqueString;
	}
	/*Determines if there are sequences in the diagonals 
	 from the upper left corner to the lower right corner*/
	private int oblique(String[] adnIn) {
		int times = 0;
		int pos = 0;
		Map<Integer, Integer> newPosition = new HashMap<Integer, Integer>();
		String obliqueString = "";
			obliqueString = "";
			obliqueString = obliquePrincipal(adnIn);
			times += searchSequence(obliqueString);
			for(int iteracion = 0; iteracion < adnIn.length; iteracion++ ) {
				if(adnIn.length - iteracion > minLength) {
					obliqueString = ""; 
					for(int p = 0; p < position.size(); p++) {
						 pos = position.get(p)+1;
						 if(pos < adnIn.length) {
							 obliqueString += adnIn[p].charAt(pos);
							 newPosition.put(p, pos); 
						 }
					}
					times += searchSequence(obliqueString);
					position.clear();
					for(int key = 0; key < newPosition.size(); key++ ) {
						position.put(key,newPosition.get(key));	
					}
					newPosition.clear();
				} else {
					break;
				}
			}
		   obliqueString = obliquePrincipal(adnIn);
           for(int a = 1; a < adnIn.length; a++) {
	        	   obliqueString = "";
	        	   if((adnIn.length - a) >= minLength) {
	        		   for(int iteraciones = a; iteraciones < adnIn.length; iteraciones++) {
		   	   				int posC = position.get(iteraciones);
		   	   				if(posC > 0)
		   	   					posC--;
		   	   				obliqueString += adnIn[iteraciones].charAt(posC);
		   	   				position.replace(iteraciones, posC);
	      			    }
	      				times += searchSequence(obliqueString);
	      				obliqueString = "";
	        	   } else {
	        		   break;
	        	   }
	           }
        log.info(String.format("Number of sequences in main oblique: %s",times));	
        
		return times;
	}
	/*Determines if there are sequences in the diagonals 
	 * from the upper right corner to the lower left corner*/
	private int obliqueInv(String[] adnIn) {
		int times = 0;
		String obliqueString = "";
		obliqueString = obliqueInverse(adnIn);
		times += searchSequence(obliqueString);
		int van = 1;
		for(int a = 0; a < adnIn.length - 1; a++) {
     	   obliqueString = "";
     	   if((adnIn.length - van) >= minLength) {
     		   for(int iteraciones = 0; iteraciones < adnIn.length - van; iteraciones++) {
	   	   				int posC = position.get(iteraciones);
	   	   				if(posC > 0)
	   	   					posC--;
	   	   				obliqueString += adnIn[iteraciones].charAt(posC);
	   	   				position.replace(iteraciones, posC);
   			    }
   				times += searchSequence(obliqueString);
   			    van++;
     	   } else {
     		   break;
     	   }
        }
		obliqueString = obliqueInverse(adnIn);
		van = 1;
		for(int a = 1; a < adnIn.length - 1; a++) {
     	   obliqueString = "";
     	   if((adnIn.length - van) >= minLength) {
     		   for(int iteraciones = a; iteraciones < adnIn.length; iteraciones++) {
	   	   				int posC = position.get(iteraciones);
	   	   				if(posC >= 0)
	   	   					posC++;
	   	   				obliqueString += adnIn[iteraciones].charAt(posC);
	   	   				position.replace(iteraciones, posC);
   			    }
   				times += searchSequence(obliqueString);
   			    van++;
     	   } else {
     		   break;
     	   }
        }
		log.info(String.format("Number of sequences in inverse oblique: %s",times));
		return times;
	}
}



