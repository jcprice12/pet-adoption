package com.john.price.PetAdoption.Functions;

import org.springframework.stereotype.Component;

import com.john.price.PetAdoption.Models.Breed;
import com.john.price.PetAdoption.Models.DogBreed;

@Component
public class BreedToDogBreedWithoutDogsMapper implements BreedToBreedMapper<DogBreed>{

	@Override
	public DogBreed apply(Breed breed) {
		DogBreed dogBreed = new DogBreed();
		dogBreed.setId(breed.getId());
		dogBreed.setName(breed.getName());
		dogBreed.setDogs(null);
		return dogBreed;
	}
}
