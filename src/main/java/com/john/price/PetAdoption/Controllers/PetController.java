package com.john.price.PetAdoption.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.john.price.PetAdoption.Response.PetResponse;
import com.john.price.PetAdoption.Service.PetResponseMapper;


@RestController
@RequestMapping(path = "/pets", produces = "application/json")
public class PetController {

	@Autowired
	PetResponseMapper mapper;
	
	@GetMapping(path = "")
    public Iterable<PetResponse> allPets() {
		return mapper.mapPets();
    }
    
    @GetMapping(path = "/{petId}")
    public PetResponse petById(@PathVariable("petId") Integer petId) {
    	return mapper.mapPet(petId);
    }

}
