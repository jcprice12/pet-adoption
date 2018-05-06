package com.john.price.PetAdoption.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.john.price.PetAdoption.Models.PetWithBreeds;
import com.john.price.PetAdoption.Services.PetWithBreedsResponseMapper;

public abstract class PetWithBreedsController {
	
	abstract PetWithBreedsResponseMapper getMapper();
	
	@GetMapping(path = "")
    public Iterable<? extends PetWithBreeds> all() {
		return getMapper().mapPets();
    }
    
    @GetMapping(path = "/{petId}")
    public PetWithBreeds byId(@PathVariable("petId") Integer petId) {
    	return getMapper().mapPet(petId);
    }
    
    @PostMapping(path = "")
    public PetWithBreeds createPet(@RequestBody PetWithBreeds petWithBreeds) {
    	return getMapper().createPetWithBreeds(petWithBreeds);
    }
    
    @PutMapping(path = "")
    public PetWithBreeds editPet(@RequestBody PetWithBreeds petWithBreeds) {
    	return getMapper().editPetWithBreeds(petWithBreeds);
    }
}
