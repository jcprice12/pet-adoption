package com.john.price.PetAdoption.TestHelpers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.john.price.PetAdoption.Models.Breed;
import com.john.price.PetAdoption.Models.Cat;
import com.john.price.PetAdoption.Models.CatBreed;
import com.john.price.PetAdoption.Models.Dog;
import com.john.price.PetAdoption.Models.DogBreed;
import com.john.price.PetAdoption.Responses.PetWithBreedsResponse;

public abstract class Builders {
	
	public static PetWithBreedsResponse buildPetWithBreedsResponse(String petType, int id) {
		Set<Breed> breeds = new HashSet<Breed>();
		if(petType.equals("cat")) {
			breeds.add(new CatBreed());
			return new PetWithBreedsResponse(new Cat(id, ("name" + id), "imageUrl", "descrip", breeds));
		} else {
			breeds.add(new DogBreed());
			return new PetWithBreedsResponse(new Dog(id, ("name" + id), "imageUrl", "descrip", breeds));
		}
	}
	
	public static Iterable<PetWithBreedsResponse> buildPetsWithBreedsResponse(int length, String petType){
		List<PetWithBreedsResponse> pets = new ArrayList<PetWithBreedsResponse>();
		for(int i = 0; i < length; i++) {
			pets.add(buildPetWithBreedsResponse(petType, i));
		}
		return pets;
	}
}
