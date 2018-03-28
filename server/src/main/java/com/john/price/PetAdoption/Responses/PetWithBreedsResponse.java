package com.john.price.PetAdoption.Responses;

import java.util.ArrayList;
import java.util.List;

import com.john.price.PetAdoption.Models.Breed;
import com.john.price.PetAdoption.Models.PetWithBreeds;

public class PetWithBreedsResponse extends PetResponse{
	private List<BreedResponse> breeds;
	
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
	
	public List<BreedResponse> getBreeds() {
		return breeds;
	}
	
	public void setBreeds(List<BreedResponse> breeds) {
		this.breeds = breeds;
	}
}
