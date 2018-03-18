package com.john.price.PetAdoption.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.john.price.PetAdoption.Services.PetWithBreedsResponseMapper;


@RestController
@RequestMapping(path = "/dogs", produces = "application/json")
public class DogController extends PetWithBreedsController{
	
	@Autowired
	@Qualifier("DogResponseMapper")
	PetWithBreedsResponseMapper mapper;
	
	@Override
	PetWithBreedsResponseMapper getMapper() {
		return mapper;
	}
}
