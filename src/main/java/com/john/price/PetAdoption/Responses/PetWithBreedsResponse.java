package com.john.price.PetAdoption.Responses;

import java.util.ArrayList;

import com.john.price.PetAdoption.Models.Breed;
import com.john.price.PetAdoption.Models.Pet;

public class PetWithBreedsResponse extends PetResponse{
	private ArrayList<BreedResponse> breeds;
	
	public <T extends Breed> PetWithBreedsResponse(Pet pet, Iterable<T> breeds) {
		super(pet.getId(), pet.getName());
		this.breeds = new ArrayList<BreedResponse>();
		for(Breed breed : breeds) {
			this.breeds.add(new BreedResponse(breed.getId(), breed.getName()));
		}
	}
	
	public ArrayList<BreedResponse> getBreeds() {
		return breeds;
	}
	
	public void setBreeds(ArrayList<BreedResponse> breeds) {
		this.breeds = breeds;
	}
}
