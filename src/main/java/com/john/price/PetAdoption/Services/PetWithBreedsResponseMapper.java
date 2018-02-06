package com.john.price.PetAdoption.Services;

import java.util.ArrayList;
import java.util.List;

import com.john.price.PetAdoption.Models.PetWithBreeds;
import com.john.price.PetAdoption.Responses.PetWithBreedsResponse;

public abstract class PetWithBreedsResponseMapper {
	protected abstract Iterable<? extends PetWithBreeds> getAllPets();
	protected abstract PetWithBreeds gePet(Integer id);
	
	public Iterable<PetWithBreedsResponse> mapPets() {
		Iterable<? extends PetWithBreeds> data = getAllPets();
		List<PetWithBreedsResponse> dogs = new ArrayList<PetWithBreedsResponse>();
		for(PetWithBreeds petWithBreeds : data) {
			dogs.add(new PetWithBreedsResponse(petWithBreeds.getId(), petWithBreeds.getName(), petWithBreeds.getBreeds()));
		}
		return dogs;
	}

	public PetWithBreedsResponse mapPet(Integer id) {
		PetWithBreeds data = gePet(id);
		return new PetWithBreedsResponse(data.getId(), data.getName(), data.getBreeds());
	}
}
