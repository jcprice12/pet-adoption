package com.john.price.PetAdoption.Services;

import java.util.HashSet;
import java.util.Set;

import com.john.price.PetAdoption.Models.Breed;
import com.john.price.PetAdoption.Models.PetWithBreeds;

public abstract class PetWithBreedsResponseMapper {
	
	protected abstract Iterable<? extends PetWithBreeds> getAllPets();
	protected abstract Set<? extends Breed> getBreedsFromListOfIds(Set<Integer> ids, PetWithBreeds petWithBreeds);
	protected abstract Set<? extends Breed> getBreedsThatHavePetWithBreeds(Integer id);
	protected abstract PetWithBreeds getPet(Integer id);
	protected abstract PetWithBreeds savePetWithBreeds(PetWithBreeds petWithBreeds);
	protected abstract Breed addPetWithBreedsToBreed(PetWithBreeds petWithBreeds, Breed breed);
	protected abstract void removePetWithBreedsFromBreed(Integer petWithBreedsId, Breed breed);
	protected abstract void saveBreeds(Set<? extends Breed> breeds);
	protected abstract void nullifyPetsInBreed(Breed breed);
	
	private Set<Integer> getBreedIdsFromPetWithBreeds(PetWithBreeds petWithBreeds){
		Set<Integer> breedIds = new HashSet<Integer>();	
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
	
	public Iterable<? extends PetWithBreeds> mapPets() {
		Iterable<? extends PetWithBreeds> data = getAllPets();		
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
	
	public PetWithBreeds createPetWithBreeds(PetWithBreeds petWithBreeds) { 	
    	Set<Integer> breedIds = getBreedIdsFromPetWithBreeds(petWithBreeds);   	
    	Set<? extends Breed> breeds = getBreedsFromListOfIds(breedIds, petWithBreeds); 	
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
	
	public PetWithBreeds editPetWithBreeds(PetWithBreeds petWithBreeds) {		
		Set<? extends Breed> breedsToRemovePetFrom = getBreedsThatHavePetWithBreeds(petWithBreeds.getId());		
		for(Breed breed : breedsToRemovePetFrom) {
			removePetWithBreedsFromBreed(petWithBreeds.getId(), breed);
		}		
		saveBreeds(breedsToRemovePetFrom);		
		return createPetWithBreeds(petWithBreeds);
	}
}
