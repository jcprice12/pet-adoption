package com.john.price.PetAdoption.Responses;

import java.util.ArrayList;

import com.john.price.PetAdoption.Models.Breed;
import com.john.price.PetAdoption.Models.PetWithBreeds;

public class PetWithBreedsResponse extends PetResponse{
	private ArrayList<BreedResponse> breeds;
	
	public PetWithBreedsResponse() {
		super();
	}
	
	public <T extends Breed> PetWithBreedsResponse(PetWithBreeds petWithBreeds) {
		super(petWithBreeds.getId(), petWithBreeds.getName());
		this.breeds = new ArrayList<BreedResponse>();
		for(Breed breed : petWithBreeds.getBreeds()) {
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
