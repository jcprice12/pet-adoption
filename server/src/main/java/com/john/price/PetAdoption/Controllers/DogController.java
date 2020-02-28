package com.john.price.PetAdoption.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.john.price.PetAdoption.Models.Dog;
import com.john.price.PetAdoption.Services.DogService;
import com.john.price.PetAdoption.Services.PetService;

@RestController
@RequestMapping(path = "/dogs")
public class DogController extends PetController<Dog> {
	
	@Autowired
	private DogService dogService;

	@Override
	protected PetService<Dog> getService() {
		return dogService;
	}
}