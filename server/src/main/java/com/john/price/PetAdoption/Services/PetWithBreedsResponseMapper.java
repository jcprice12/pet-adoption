package com.john.price.PetAdoption.Services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.john.price.PetAdoption.Models.Breed;
import com.john.price.PetAdoption.Models.PetWithBreeds;

public abstract class PetWithBreedsResponseMapper {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	protected abstract Iterable<? extends PetWithBreeds> getAllPets();
	protected abstract PetWithBreeds getPet(Integer id);
	protected abstract PetWithBreeds savePetWithBreeds(PetWithBreeds petWithBreeds);
	protected abstract String getIntersectionTableInsertionString();
	protected abstract String getIntersectionTableDeletionString();
	protected abstract Breed mapBreed(Breed breed);
	
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
	
	@Transactional
	public PetWithBreeds mapNewPet(PetWithBreeds petWithBreeds) {
		PetWithBreeds savedPetWithBreeds = savePetWithBreeds(petWithBreeds);
    	insertIntoIntersectionTable(savedPetWithBreeds);
    	return savedPetWithBreeds;
	}
	
	@Transactional
	public PetWithBreeds mapEditedPet(PetWithBreeds petWithBreeds) {
		jdbcTemplate.update(getIntersectionTableDeletionString(), petWithBreeds.getId());
		return mapNewPet(petWithBreeds);
	}
	
	private void insertIntoIntersectionTable(PetWithBreeds petWithBreeds) {
		List<Object[]> parameters = new ArrayList<Object[]>();
	    for (Breed breed : petWithBreeds.getBreeds()) {
	        parameters.add(new Object[] {breed.getId(), petWithBreeds.getId()});
	    }
		jdbcTemplate.batchUpdate(getIntersectionTableInsertionString(), parameters);
	}
	
	private void removePetsFromBreeds(PetWithBreeds petWithBreeds) {
		Set<Breed> breeds = petWithBreeds.getBreeds();
		Set<Breed> newBreeds = new HashSet<Breed>();
		for(Breed breed : breeds) {
			newBreeds.add(mapBreed(breed));
		}
		petWithBreeds.setBreeds(newBreeds);
	}
}
