package com.john.price.PetAdoption.Functions;

import org.springframework.stereotype.Component;

import com.john.price.PetAdoption.Models.Cat;

@Component
public class CatToCatWithoutBreedsMapper implements PetToPetMapper<Cat, Cat> {

	@Override
	public Cat apply(Cat originalCat) {
		Cat newCat = new Cat();

		newCat.setId(originalCat.getId());
		newCat.setName(originalCat.getName());
		newCat.setImage(originalCat.getImage());
		newCat.setDescription(originalCat.getDescription());

		return newCat;
	}

}
