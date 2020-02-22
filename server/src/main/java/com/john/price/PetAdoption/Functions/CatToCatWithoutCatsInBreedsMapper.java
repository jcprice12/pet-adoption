package com.john.price.PetAdoption.Functions;

import org.springframework.stereotype.Component;

import com.john.price.PetAdoption.Models.Cat;

@Component
public class CatToCatWithoutCatsInBreedsMapper implements PetToPetMapper<Cat, Cat> {
	
	@Override
	public Cat apply(Cat cat) {
		return new Cat(cat);
	}
}
