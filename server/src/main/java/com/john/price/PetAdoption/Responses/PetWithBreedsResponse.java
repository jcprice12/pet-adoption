package com.john.price.PetAdoption.Responses;

import java.util.ArrayList;
import java.util.List;

import com.john.price.PetAdoption.Models.Breed;
import com.john.price.PetAdoption.Models.Pet;
import com.john.price.PetAdoption.Models.PetWithBreeds;

public class PetWithBreedsResponse extends Pet{
	private List<Breed> breeds;
	
	public PetWithBreedsResponse() {
		super();
	}
	
	public <T extends Breed> PetWithBreedsResponse(PetWithBreeds petWithBreeds) {
		super(petWithBreeds.getId(), petWithBreeds.getName(), petWithBreeds.getImage(), petWithBreeds.getDescription());
		this.breeds = new ArrayList<Breed>();
		for(Breed breed : petWithBreeds.getBreeds()) {
			this.breeds.add(new Breed(breed.getId(), breed.getName()));
		}
	}

	public List<Breed> getBreeds() {
		return breeds;
	}

	public void setBreeds(List<Breed> breeds) {
		this.breeds = breeds;
	}
}
