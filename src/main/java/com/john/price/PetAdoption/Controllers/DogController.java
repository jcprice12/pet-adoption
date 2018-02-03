package com.john.price.PetAdoption.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.john.price.PetAdoption.Responses.PetResponse;
import com.john.price.PetAdoption.Services.PetResponseMapper;


@RestController
@RequestMapping(path = "/dogs", produces = "application/json")
public class DogController {

	@Autowired
	@Qualifier("DogResponseMapper")
	PetResponseMapper mapper;
	
	@GetMapping(path = "")
    public Iterable<PetResponse> allDogs() {
		return mapper.mapPets();
    }
    
    @GetMapping(path = "/{dogId}")
    public PetResponse dogById(@PathVariable("dogId") Integer dogId) {
    	return mapper.mapPet(dogId);
    }

}
