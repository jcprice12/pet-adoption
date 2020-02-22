package com.john.price.PetAdoption.Functions;

import org.springframework.stereotype.Component;

import com.john.price.PetAdoption.Models.Dog;

@Component
public class DogToDogWithoutDogsInBreedsMapper implements PetToPetMapper<Dog, Dog>{
	
	@Override
	public Dog apply(Dog dog) {
		return new Dog(dog);
	}
}
