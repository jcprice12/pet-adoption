package com.john.price.PetAdoption.Functions;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.john.price.PetAdoption.Models.Cat;

@Component
public class CatToCatWithoutCatsInBreedsMapper implements PetToPetMapper<Cat, Cat> {

	@Autowired
	private BreedToCatBreedWithoutCatsMapper breedToCatBreedWithoutCatsMapper;
	
	@Override
	public Cat apply(Cat cat) {
		Cat responseCat = new Cat();
		responseCat.setDescription(cat.getDescription());
		responseCat.setId(cat.getId());
		responseCat.setImage(cat.getImage());
		responseCat.setName(cat.getName());
		responseCat.setBreeds(cat.getBreeds().stream().map(breedToCatBreedWithoutCatsMapper).collect(Collectors.toSet()));
		return responseCat;
	}
}
