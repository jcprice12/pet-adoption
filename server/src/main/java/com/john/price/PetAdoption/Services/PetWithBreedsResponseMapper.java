package com.john.price.PetAdoption.Services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.john.price.PetAdoption.Models.Breed;
import com.john.price.PetAdoption.Models.PetWithBreeds;
import com.john.price.PetAdoption.Responses.BreedResponse;
import com.john.price.PetAdoption.Responses.PetWithBreedsResponse;

public abstract class PetWithBreedsResponseMapper {
	
	protected abstract Iterable<? extends PetWithBreeds> getAllPets();
	protected abstract PetWithBreeds getPet(Integer id);
	protected abstract PetWithBreeds instantiatePetWithBreeds();
	protected abstract Breed instantiateBreed();
	protected abstract PetWithBreeds savePetWithBreeds(PetWithBreeds petWithBreeds);
	
	public Iterable<PetWithBreedsResponse> mapPets() {
		Iterable<? extends PetWithBreeds> data = getAllPets();
		List<PetWithBreedsResponse> dogs = new ArrayList<PetWithBreedsResponse>();
		for(PetWithBreeds petWithBreeds : data) {
			dogs.add(new PetWithBreedsResponse(petWithBreeds));
		}
		return dogs;
	}

	public PetWithBreedsResponse mapPet(Integer id) {
		PetWithBreeds data = getPet(id);
		return new PetWithBreedsResponse(data);
	}
	
	public PetWithBreeds createPetWithBreeds(PetWithBreedsResponse petWithBreedsResponse) {
		PetWithBreeds petWithBreeds = instantiatePetWithBreeds();
		
		petWithBreeds.setName(petWithBreedsResponse.getName());
    	petWithBreeds.setDescription(petWithBreedsResponse.getDescription());
    	petWithBreeds.setImage(petWithBreedsResponse.getImage());
    	petWithBreeds.setBreeds(new HashSet<Breed>());
    	
    	Breed tempBreed;
    	for(BreedResponse breedResponse : petWithBreedsResponse.getBreeds()) {
    		tempBreed = instantiateBreed();
    		tempBreed.setName(breedResponse.getName());
    		tempBreed.setId(breedResponse.getId());
    		petWithBreeds.getBreeds().add(tempBreed);
    	}

    	return savePetWithBreeds(petWithBreeds);
	}
}
