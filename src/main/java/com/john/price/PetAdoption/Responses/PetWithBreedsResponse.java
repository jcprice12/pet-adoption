package com.john.price.PetAdoption.Responses;

import java.util.ArrayList;

import com.john.price.PetAdoption.Models.Breed;

public class PetWithBreedsResponse extends PetResponse{
	private ArrayList<BreedResponse> breeds;
	
	public <T extends Breed> PetWithBreedsResponse(Integer id, String name, Iterable<T> breeds) {
		super(id, name);
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
