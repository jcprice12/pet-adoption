package com.john.price.PetAdoption.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.john.price.PetAdoption.Services.PetWithBreedsResponseMapper;

@RestController
@RequestMapping(path = "/cats", produces = "application/json")
public class CatController extends PetWithBreedsController{
	
	@Autowired
	@Qualifier("CatResponseMapper")
	PetWithBreedsResponseMapper mapper;
	
	@Override
	PetWithBreedsResponseMapper getMapper() {
		return mapper;
	}
    
}
