package com.john.price.PetAdoption.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.john.price.PetAdoption.Responses.DogResponse;
import com.john.price.PetAdoption.Services.DogResponseMapper;


@RestController
@RequestMapping(path = "/dogs", produces = "application/json")
public class DogController {

	@Autowired
	DogResponseMapper mapper;
	
	@GetMapping(path = "")
    public Iterable<DogResponse> allDogs() {
		return mapper.mapPets();
    }
    
    @GetMapping(path = "/{dogId}")
    public DogResponse dogById(@PathVariable("dogId") Integer dogId) {
    	return mapper.mapPet(dogId);
    }

}
