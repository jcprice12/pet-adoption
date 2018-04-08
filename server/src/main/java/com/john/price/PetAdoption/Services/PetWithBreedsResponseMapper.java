package com.john.price.PetAdoption.Services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import com.john.price.PetAdoption.Models.Breed;
import com.john.price.PetAdoption.Models.PetWithBreeds;
import com.john.price.PetAdoption.Responses.BreedResponse;
import com.john.price.PetAdoption.Responses.PetWithBreedsResponse;

public abstract class PetWithBreedsResponseMapper {
	
	protected abstract Iterable<? extends PetWithBreeds> getAllPets();
	protected abstract PetWithBreeds getPet(Integer id);
	protected abstract PetWithBreeds instantiatePetWithBreeds();
	protected abstract PetWithBreeds savePetWithBreeds(PetWithBreeds petWithBreeds);
	protected abstract List<? extends Breed> getBreedsFromListOfIds(List<Integer> ids, PetWithBreeds petWithBreeds);
	protected abstract Breed addPetWithBreedsToBreed(PetWithBreeds petWithBreeds, Breed breed);
	protected abstract void saveBreeds(List<? extends Breed> breeds);
	
	public Iterable<PetWithBreedsResponse> mapPets() {
		Iterable<? extends PetWithBreeds> data = getAllPets();
		List<PetWithBreedsResponse> dogs = new ArrayList<PetWithBreedsResponse>();
		for(PetWithBreeds petWithBreeds : data) {
			dogs.add(new PetWithBreedsResponse(petWithBreeds));
		}
		return dogs;
	}

	public PetWithBreedsResponse mapPet(Integer id) {
		return new PetWithBreedsResponse(getPet(id));
	}
	
	@Transactional
	public PetWithBreeds createPetWithBreeds(PetWithBreedsResponse petWithBreedsResponse) {
		PetWithBreeds petWithBreeds = instantiatePetWithBreeds();
    	
    	List<Integer> breedIds = new ArrayList<Integer>();
    	for(BreedResponse breedResponse : petWithBreedsResponse.getBreeds()) {
    		breedIds.add(breedResponse.getId());
    	}
    	
    	List<? extends Breed> breeds = getBreedsFromListOfIds(breedIds, petWithBreeds);  	
    	Set<Breed> breedsSet = new HashSet<Breed>();
    	for(Breed breed : breeds) {
    		breed = addPetWithBreedsToBreed(petWithBreeds, breed);
    		breedsSet.add(breed);
    	}
    	
    	petWithBreeds.setBreeds(breedsSet);
    	petWithBreeds.setName(petWithBreedsResponse.getName());
    	petWithBreeds.setDescription(petWithBreedsResponse.getDescription());
    	petWithBreeds.setImage(petWithBreedsResponse.getImage());
    	
    	petWithBreeds = savePetWithBreeds(petWithBreeds);
    	saveBreeds(breeds);
    	
    	return petWithBreeds;
	}
}
