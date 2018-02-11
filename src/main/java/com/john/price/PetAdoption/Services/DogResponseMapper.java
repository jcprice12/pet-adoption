package com.john.price.PetAdoption.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.john.price.PetAdoption.Models.PetWithBreeds;
import com.john.price.PetAdoption.Repositories.DogRepository;

@Component("DogResponseMapper")
public class DogResponseMapper extends PetWithBreedsResponseMapper{
	
	@Autowired
	private DogRepository repository;

	@Override
	protected Iterable<? extends PetWithBreeds> getAllPets() {
		return repository.findAll();
	}

	@Override
	protected PetWithBreeds gePet(Integer id) {
		return repository.findOne(id);
	}

}
