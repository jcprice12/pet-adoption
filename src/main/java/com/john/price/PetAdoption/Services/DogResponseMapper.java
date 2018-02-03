package com.john.price.PetAdoption.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.john.price.PetAdoption.Models.Dog;
import com.john.price.PetAdoption.Repositories.DogRepository;
import com.john.price.PetAdoption.Responses.DogResponse;

@Component
public class DogResponseMapper {
	@Autowired 
	private DogRepository dogRepository;
	
	public Iterable<DogResponse> mapPets() {
		Iterable<Dog> data = dogRepository.findAll();
		List<DogResponse> dogs = new ArrayList<DogResponse>();
		for(Dog dog : data) {
			dogs.add(new DogResponse(dog.getId(), dog.getName(), dog.getBreeds()));
		}
		return dogs;
	}
	
	public DogResponse mapPet(Integer id) {
		Dog data = dogRepository.findOne(id);
		return new DogResponse(data.getId(), data.getName(), data.getBreeds());
	}
}
