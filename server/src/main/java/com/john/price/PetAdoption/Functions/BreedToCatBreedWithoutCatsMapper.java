package com.john.price.PetAdoption.Functions;

import org.springframework.stereotype.Component;

import com.john.price.PetAdoption.Models.Breed;
import com.john.price.PetAdoption.Models.CatBreed;

@Component
public class BreedToCatBreedWithoutCatsMapper implements BreedToBreedMapper<CatBreed> {

	@Override
	public CatBreed apply(Breed breed) {
		CatBreed catBreed = new CatBreed();
		catBreed.setId(breed.getId());
		catBreed.setName(breed.getName());
		catBreed.setCats(null);
		return catBreed;
	}
}
