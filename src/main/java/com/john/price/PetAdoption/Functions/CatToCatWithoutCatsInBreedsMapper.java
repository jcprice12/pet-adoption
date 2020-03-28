package com.john.price.PetAdoption.Functions;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.john.price.PetAdoption.Models.Cat;
import com.john.price.PetAdoption.Models.CatBreed;

@Component
public class CatToCatWithoutCatsInBreedsMapper implements PetToPetMapper<Cat, Cat> {

	@Autowired
	private CatToCatWithoutBreedsMapper catToCatWithoutBreedsMapper;
	
	@Override
	public Cat apply(Cat originalCat) {
		Cat newCatWithBreeds = catToCatWithoutBreedsMapper.apply(originalCat);
		
		newCatWithBreeds.setBreeds(originalCat.getBreeds().stream().map(breed -> {
			CatBreed newBreed = new CatBreed();
			newBreed.setId(breed.getId());
			newBreed.setName(breed.getName());
			return newBreed;
		}).collect(Collectors.toSet()));

		return newCatWithBreeds;
	}
}
