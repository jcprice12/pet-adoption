package com.john.price.PetAdoption.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.john.price.PetAdoption.Models.Dog;
import com.john.price.PetAdoption.Models.PetWithBreeds;
import com.john.price.PetAdoption.Repositories.DogRepository;

@Component("DogResponseMapper")
public class DogResponseMapper extends PetWithBreedsResponseMapper{
	@Autowired
	@Qualifier("DogRepository")
	private DogRepository repository;

	@Override
	protected Iterable<Dog> getAllPets() {
		return repository.findAll();
	}

	@Override
	protected PetWithBreeds gePet(Integer id) {
		return repository.findOne(id);
	}

}
