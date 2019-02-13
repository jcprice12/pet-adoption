package com.john.price.PetAdoption.Controllers;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.john.price.PetAdoption.Models.Pet;
import com.john.price.PetAdoption.Services.PetService;

public abstract class PetController<P extends Pet> {
	
	protected abstract PetService<P> getService();

	@GetMapping(path = "")
    public Iterable<P> getPets() {
		return getService().getPets();
    }
    
    @GetMapping(path = "/{petId}")
    public P getPet(@PathVariable("petId") Integer petId) {
    	return getService().getPet(petId);
    }

	@PostMapping(path = "")
    public P createPet(@RequestBody @Validated({javax.validation.groups.Default.class, Pet.PetPostValidation.class}) P pet) {
    	return getService().getPet(getService().createPet(pet).getId());
    }

    @PutMapping(path = "")
    public P editPet(@RequestBody @Validated({javax.validation.groups.Default.class, Pet.PetPutValidation.class}) P pet) {
    	return getService().getPet(getService().editPet(pet).getId());
    }
}