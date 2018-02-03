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
	private DogRepository petRepository;
	
	public Iterable<DogResponse> mapPets() {
		Iterable<Dog> data = petRepository.findAll();
		List<DogResponse> dogs = new ArrayList<DogResponse>();
		for(Dog dog : data) {
			dogs.add(new DogResponse(dog.getDogId(), dog.getName(), dog.getDogBreeds()));
		}
		return dogs;
	}
	
	public DogResponse mapPet(Integer id) {
		Dog data = petRepository.findOne(id);
		return new DogResponse(data.getDogId(), data.getName(), data.getDogBreeds());
	}
}
