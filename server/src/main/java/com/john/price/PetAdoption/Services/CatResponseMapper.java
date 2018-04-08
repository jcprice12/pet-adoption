package com.john.price.PetAdoption.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.john.price.PetAdoption.Models.Breed;
import com.john.price.PetAdoption.Models.Cat;
import com.john.price.PetAdoption.Models.CatBreed;
import com.john.price.PetAdoption.Models.PetWithBreeds;
import com.john.price.PetAdoption.Repositories.CatBreedRepository;
import com.john.price.PetAdoption.Repositories.CatRepository;

@Component("CatResponseMapper")
public class CatResponseMapper extends PetWithBreedsResponseMapper {

	@Autowired 
	private CatRepository catRepository;
	
	@Autowired 
	private CatBreedRepository catBreedRepository;

	@Override
	protected Iterable<Cat> getAllPets() {
		return catRepository.findAll();
	}

	@Override
	protected Cat getPet(Integer id) {
		return catRepository.findOne(id);
	}
	
	@Override
	protected PetWithBreeds instantiatePetWithBreeds() {
		return new Cat();
	}
	
	@Override
	protected PetWithBreeds savePetWithBreeds(PetWithBreeds petWithBreeds) {
		return catRepository.save((Cat)petWithBreeds);
	}

	@Override
	protected List<? extends Breed> getBreedsFromListOfIds(List<Integer> ids, PetWithBreeds petWithBreeds) {
		return catBreedRepository.findByIdIn(ids);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void saveBreeds(List<? extends Breed> breeds) {
		catBreedRepository.save((List<CatBreed>)breeds);		
	}

	@Override
	protected Breed addPetWithBreedsToBreed(PetWithBreeds petWithBreeds, Breed breed) {
		CatBreed catBreed = (CatBreed) breed;
		catBreed.getCats().add((Cat)petWithBreeds);
		return catBreed;
	}
}
