package com.john.price.PetAdoption.Services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import com.john.price.PetAdoption.Models.Breed;
import com.john.price.PetAdoption.Models.PetWithBreeds;
import com.john.price.PetAdoption.Responses.PetWithBreedsResponse;

public abstract class PetWithBreedsResponseMapper {
	
	protected abstract Iterable<? extends PetWithBreeds> getAllPets();
	protected abstract PetWithBreeds getPet(Integer id);
	protected abstract PetWithBreeds instantiatePetWithBreeds();
	protected abstract PetWithBreeds savePetWithBreeds(PetWithBreeds petWithBreeds);
	protected abstract List<? extends Breed> getBreedsFromListOfIds(List<Integer> ids, PetWithBreeds petWithBreeds);
	protected abstract List<? extends Breed> getBreedsThatHavePetWithBreeds(Integer id);
	protected abstract Breed addPetWithBreedsToBreed(PetWithBreeds petWithBreeds, Breed breed);
	protected abstract void removePetWithBreedsFromBreed(Integer petWithBreedsId, Breed breed);
	protected abstract void saveBreeds(List<? extends Breed> breeds);
	
	private List<Integer> getBreedIdsFromPetWithBreedsResponse(PetWithBreedsResponse petWithBreedsResponse){
		List<Integer> breedIds = new ArrayList<Integer>();
    	for(Breed breed : petWithBreedsResponse.getBreeds()) {
    		breedIds.add(breed.getId());
    	}
    	return breedIds;
	}
	
	private PetWithBreeds getPetFromResponse(PetWithBreedsResponse petWithBreedsResponse) {
		PetWithBreeds petWithBreeds = instantiatePetWithBreeds();
		petWithBreeds.setName(petWithBreedsResponse.getName());
    	petWithBreeds.setDescription(petWithBreedsResponse.getDescription());
    	petWithBreeds.setImage(petWithBreedsResponse.getImage());
    	petWithBreeds.setId(petWithBreedsResponse.getId());
    	return petWithBreeds;
	}
	
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
	public PetWithBreedsResponse createPetWithBreeds(PetWithBreedsResponse petWithBreedsResponse) {
		PetWithBreeds petWithBreeds = getPetFromResponse(petWithBreedsResponse);
    	
    	List<Integer> breedIds = getBreedIdsFromPetWithBreedsResponse(petWithBreedsResponse);   	
    	List<? extends Breed> breeds = getBreedsFromListOfIds(breedIds, petWithBreeds);  	
    	Set<Breed> breedsSet = new HashSet<Breed>();
    	for(Breed breed : breeds) {
    		breed = addPetWithBreedsToBreed(petWithBreeds, breed);
    		breedsSet.add(breed);
    	}
    	
    	petWithBreeds.setBreeds(breedsSet);
    	
    	petWithBreeds = savePetWithBreeds(petWithBreeds);
    	saveBreeds(breeds);
    	
    	return new PetWithBreedsResponse(petWithBreeds);
	}
	
	@Transactional
	public PetWithBreedsResponse editPetWithBreeds(PetWithBreedsResponse petWithBreedsResponse) {		
		List<? extends Breed> breedsToRemovePetFrom = getBreedsThatHavePetWithBreeds(petWithBreedsResponse.getId());
		for(Breed breed : breedsToRemovePetFrom) {
			removePetWithBreedsFromBreed(petWithBreedsResponse.getId(), breed);
		}
		
		return createPetWithBreeds(petWithBreedsResponse);
	}
}
