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

import com.john.price.PetAdoption.Models.Cat;
import com.john.price.PetAdoption.Models.Pet;
import com.john.price.PetAdoption.Models.PetWithBreeds;
import com.john.price.PetAdoption.Services.CatService;

@RestController
@RequestMapping(path = "/cats", produces = "application/json")
public class CatController{
	
	@Autowired
	private CatService catService;
	
	@GetMapping(path = "")
    public Iterable<? extends PetWithBreeds> getCats() {	
		return catService.getPets();
    }
    
    @GetMapping(path = "/{petId}")
    public PetWithBreeds getCat(@PathVariable("petId") Integer petId) {
    	return catService.getPet(petId);
    }

	@PostMapping(path = "")
    public PetWithBreeds createCat(@RequestBody @Validated({javax.validation.groups.Default.class, Pet.PetPostValidation.class}) Cat cat) {
		return catService.getPet(catService.createPet(cat).getId());
    }

    @PutMapping(path = "")
    public PetWithBreeds editCat(@RequestBody @Validated({javax.validation.groups.Default.class, Pet.PetPutValidation.class}) Cat cat) {
    	return catService.getPet(catService.editPet(cat).getId());
    }
}