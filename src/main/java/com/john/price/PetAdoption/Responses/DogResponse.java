package com.john.price.PetAdoption.Responses;

import java.util.ArrayList;

import com.john.price.PetAdoption.Models.DogBreed;
import com.john.price.PetAdoption.Responses.Structs.DogBreedStruct;

public class DogResponse extends PetResponse{
	private ArrayList<DogBreedStruct> breeds;
	
	public DogResponse(Integer id, String name, Iterable<DogBreed> breeds) {
		setId(id);
		setName(name);
		this.breeds = new ArrayList<DogBreedStruct>();
		for(DogBreed breed : breeds) {
			this.breeds.add(new DogBreedStruct(breed.getId(), breed.getName()));
		}
	}
	
	public ArrayList<DogBreedStruct> getBreeds(){
		return this.breeds;
	}
	
	public void setBreeds(ArrayList<DogBreedStruct> breeds) {
		this.breeds = breeds;
	}
}
