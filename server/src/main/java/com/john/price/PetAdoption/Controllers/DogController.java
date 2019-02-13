package com.john.price.PetAdoption.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.john.price.PetAdoption.Models.Dog;
import com.john.price.PetAdoption.Models.Pet;
import com.john.price.PetAdoption.Models.PetWithBreeds;
import com.john.price.PetAdoption.Services.DogService;


@RestController
@RequestMapping(path = "/dogs", produces = "application/json")
public class DogController {
	
	@Autowired
	private DogService dogService;
	
	@GetMapping(path = "")
    public Iterable<? extends PetWithBreeds> getDogs() {
		return dogService.getPets();
    }
    
    @GetMapping(path = "/{petId}")
    public PetWithBreeds getDog(@PathVariable("petId") Integer petId) {
    	return dogService.getPet(petId);
    }

	@PostMapping(path = "")
    public PetWithBreeds createDog(@RequestBody @Validated({javax.validation.groups.Default.class, Pet.PetPostValidation.class}) Dog dog) {
    	return dogService.getPet(dogService.createPet(dog).getId());
    }

    @PutMapping(path = "")
    public PetWithBreeds editDog(@RequestBody @Validated({javax.validation.groups.Default.class, Pet.PetPutValidation.class}) Dog dog) {
    	return dogService.getPet(dogService.editPet(dog).getId());
    }
}
