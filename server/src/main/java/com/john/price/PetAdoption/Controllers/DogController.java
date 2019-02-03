package com.john.price.PetAdoption.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.john.price.PetAdoption.Models.Dog;
import com.john.price.PetAdoption.Models.PetWithBreeds;
import com.john.price.PetAdoption.Services.PetWithBreedsResponseMapper;


@RestController
@RequestMapping(path = "/dogs", produces = "application/json")
public class DogController {
	
	@Autowired
	@Qualifier("DogResponseMapper")
	private PetWithBreedsResponseMapper mapper;
	
	@GetMapping(path = "")
    public Iterable<? extends PetWithBreeds> getDogs() {
		return mapper.mapPets();
    }
    
    @GetMapping(path = "/{petId}")
    public PetWithBreeds getDog(@PathVariable("petId") Integer petId) {
    	return mapper.mapPet(petId);
    }

	@PostMapping(path = "")
    public PetWithBreeds createDog(@RequestBody Dog dog) {
    	return mapper.createPetWithBreeds(dog);
    }

    @PutMapping(path = "")
    public PetWithBreeds editDog(@RequestBody Dog dog) {
    	return mapper.editPetWithBreeds(dog);
    }
}
