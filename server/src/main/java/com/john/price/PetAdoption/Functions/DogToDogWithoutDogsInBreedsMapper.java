package com.john.price.PetAdoption.Functions;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.john.price.PetAdoption.Models.Dog;

@Component
public class DogToDogWithoutDogsInBreedsMapper implements PetToPetMapper<Dog, Dog>{

	@Autowired
	private BreedToDogBreedWithoutDogsMapper breedToDogBreedWithoutDogsMapper;
	
	@Override
	public Dog apply(Dog dog) {
		Dog responseDog = new Dog();
		responseDog.setDescription(dog.getDescription());
		responseDog.setId(dog.getId());
		responseDog.setImage(dog.getImage());
		responseDog.setName(dog.getName());
		responseDog.setBreeds(dog.getBreeds().stream().map(breedToDogBreedWithoutDogsMapper).collect(Collectors.toSet()));
		return responseDog;
	}
}
