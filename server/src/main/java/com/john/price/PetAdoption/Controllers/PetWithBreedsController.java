package com.john.price.PetAdoption.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.john.price.PetAdoption.Responses.PetWithBreedsResponse;
import com.john.price.PetAdoption.Services.PetWithBreedsResponseMapper;

public abstract class PetWithBreedsController {
	
	abstract PetWithBreedsResponseMapper getMapper();
	
	@GetMapping(path = "")
    public Iterable<PetWithBreedsResponse> all() {
		return getMapper().mapPets();
    }
    
    @GetMapping(path = "/{petId}")
    public PetWithBreedsResponse byId(@PathVariable("petId") Integer petId) {
    	return getMapper().mapPet(petId);
    }
}
