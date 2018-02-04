package com.john.price.PetAdoption.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.john.price.PetAdoption.Models.Dog;
import com.john.price.PetAdoption.Repositories.DogRepository;
import com.john.price.PetAdoption.Responses.PetWithBreedsResponse;

@Component("DogResponseMapper")
public class DogResponseMapper implements PetWithBreedsResponseMapper{
	@Autowired 
	private DogRepository repository;
	
	public Iterable<PetWithBreedsResponse> mapPets() {
		Iterable<Dog> data = repository.findAll();
		List<PetWithBreedsResponse> dogs = new ArrayList<PetWithBreedsResponse>();
		for(Dog dog : data) {
			dogs.add(new PetWithBreedsResponse(dog, dog.getBreeds()));
		}
		return dogs;
	}
	
	public PetWithBreedsResponse mapPet(Integer id) {
		Dog data = repository.findOne(id);
		return new PetWithBreedsResponse(data, data.getBreeds());
	}

}
