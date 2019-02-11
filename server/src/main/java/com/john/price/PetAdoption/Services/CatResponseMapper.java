package com.john.price.PetAdoption.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.john.price.PetAdoption.Models.Breed;
import com.john.price.PetAdoption.Models.Cat;
import com.john.price.PetAdoption.Models.CatBreed;
import com.john.price.PetAdoption.Models.PetWithBreeds;
import com.john.price.PetAdoption.Repositories.CatRepository;

@Component("CatResponseMapper")
public class CatResponseMapper extends PetWithBreedsResponseMapper {

	@Autowired 
	private CatRepository catRepository;

	@Override
	protected Iterable<? extends PetWithBreeds> getAllPets() {
		return catRepository.findAll();
	}

	@Override
	protected Cat getPet(Integer id) {
		return catRepository.findOne(id);
	}
	
	@Override
	protected PetWithBreeds savePetWithBreeds(PetWithBreeds petWithBreeds) {
		return catRepository.save((Cat)petWithBreeds);
	}

	@Override
	protected String getIntersectionTableInsertionString() {
		return "INSERT INTO catbreed_cat (breed_id, cat_id) VALUES (?, ?)";
	}

	@Override
	protected String getIntersectionTableDeletionString() {
		return "DELETE from catbreed_cat where cat_id = ?";
	}

	@Override
	protected Breed mapBreed(Breed breed) {
		CatBreed catBreed = new CatBreed();
		catBreed.setCats(null);
		catBreed.setId(breed.getId());
		catBreed.setName(breed.getName());
		return catBreed;
	}
}
