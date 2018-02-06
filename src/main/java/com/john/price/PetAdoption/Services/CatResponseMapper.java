package com.john.price.PetAdoption.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.john.price.PetAdoption.Models.Cat;
import com.john.price.PetAdoption.Models.PetWithBreeds;
import com.john.price.PetAdoption.Repositories.CatRepository;

@Component("CatResponseMapper")
public class CatResponseMapper extends PetWithBreedsResponseMapper {

	@Autowired CatRepository repository;

	@Override
	protected Iterable<Cat> getAllPets() {
		return repository.findAll();
	}

	@Override
	protected PetWithBreeds gePet(Integer id) {
		return repository.findOne(id);
	}
	
	

}
