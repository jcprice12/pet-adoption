package com.john.price.PetAdoption.Services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import com.john.price.PetAdoption.Models.Breed;
import com.john.price.PetAdoption.Models.PetWithBreeds;

public abstract class PetWithBreedsResponseMapper {
	
	protected abstract List<? extends PetWithBreeds> getAllPets();
	protected abstract PetWithBreeds getPet(Integer id);
	protected abstract PetWithBreeds savePetWithBreeds(PetWithBreeds petWithBreeds);
	protected abstract List<? extends Breed> getBreedsFromListOfIds(List<Integer> ids, PetWithBreeds petWithBreeds);
	protected abstract List<? extends Breed> getBreedsThatHavePetWithBreeds(Integer id);
	protected abstract Breed addPetWithBreedsToBreed(PetWithBreeds petWithBreeds, Breed breed);
	protected abstract void removePetWithBreedsFromBreed(Integer petWithBreedsId, Breed breed);
	protected abstract void saveBreeds(List<? extends Breed> breeds);
	protected abstract void nullifyPetsInBreed(Breed breed);
	
	private List<Integer> getBreedIdsFromPetWithBreeds(PetWithBreeds petWithBreeds){
		List<Integer> breedIds = new ArrayList<Integer>();	
    	for(Breed breed : petWithBreeds.getBreeds()) {
    		breedIds.add(breed.getId());
    	}  	
    	return breedIds;
	}
	
	private void removePetsFromBreeds(PetWithBreeds petWithBreeds) {
		Set<Breed> breeds = petWithBreeds.getBreeds();		
		for(Breed breed : breeds) {
			nullifyPetsInBreed(breed);
		}
	}
	
	public List<? extends PetWithBreeds> mapPets() {
		List<? extends PetWithBreeds> data = getAllPets();		
		for(PetWithBreeds petWithBreeds : data) {
			removePetsFromBreeds(petWithBreeds);
		}	
		return data;
	}

	public PetWithBreeds mapPet(Integer id) {
		PetWithBreeds petWithBreeds = getPet(id);
		removePetsFromBreeds(petWithBreeds);
		return petWithBreeds;
	}
	
	@Transactional
	public PetWithBreeds createPetWithBreeds(PetWithBreeds petWithBreeds) { 	
    	List<Integer> breedIds = getBreedIdsFromPetWithBreeds(petWithBreeds);   	
    	List<? extends Breed> breeds = getBreedsFromListOfIds(breedIds, petWithBreeds); 	
    	Set<Breed> breedsSet = new HashSet<Breed>();
    	for(Breed breed : breeds) {
    		breed = addPetWithBreedsToBreed(petWithBreeds, breed);
    		breedsSet.add(breed);
    	}  	
    	petWithBreeds.setBreeds(breedsSet);  	
    	petWithBreeds = savePetWithBreeds(petWithBreeds);
    	saveBreeds(breeds);    	
    	removePetsFromBreeds(petWithBreeds);    	
    	return petWithBreeds;
	}
	
	@Transactional
	public PetWithBreeds editPetWithBreeds(PetWithBreeds petWithBreeds) {		
		List<? extends Breed> breedsToRemovePetFrom = getBreedsThatHavePetWithBreeds(petWithBreeds.getId());		
		for(Breed breed : breedsToRemovePetFrom) {
			removePetWithBreedsFromBreed(petWithBreeds.getId(), breed);
		}		
		saveBreeds(breedsToRemovePetFrom);		
		return createPetWithBreeds(petWithBreeds);
	}
}
