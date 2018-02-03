package com.john.price.PetAdoption.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.john.price.PetAdoption.Models.Dog;
import com.john.price.PetAdoption.Repositories.DogRepository;
import com.john.price.PetAdoption.Responses.DogResponse;
import com.john.price.PetAdoption.Responses.PetResponse;

@Component("DogResponseMapper")
public class DogResponseMapper implements PetResponseMapper{
	@Autowired 
	private DogRepository dogRepository;
	
	public Iterable<PetResponse> mapPets() {
		Iterable<Dog> data = dogRepository.findAll();
		List<PetResponse> dogs = new ArrayList<PetResponse>();
		for(Dog dog : data) {
			dogs.add(new DogResponse(dog.getId(), dog.getName(), dog.getBreeds()));
		}
		return dogs;
	}
	
	public PetResponse mapPet(Integer id) {
		Dog data = dogRepository.findOne(id);
		return new DogResponse(data.getId(), data.getName(), data.getBreeds());
	}

}
