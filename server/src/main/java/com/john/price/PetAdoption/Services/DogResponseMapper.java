package com.john.price.PetAdoption.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.john.price.PetAdoption.Models.Breed;
import com.john.price.PetAdoption.Models.Dog;
import com.john.price.PetAdoption.Models.DogBreed;
import com.john.price.PetAdoption.Models.PetWithBreeds;
import com.john.price.PetAdoption.Repositories.DogBreedRepository;
import com.john.price.PetAdoption.Repositories.DogRepository;

@Component("DogResponseMapper")
public class DogResponseMapper extends PetWithBreedsResponseMapper{
	
	@Autowired
	private DogRepository dogRrepository;
	
	@Autowired
	private DogBreedRepository dogBreedRepository;

	@Override
	protected Iterable<Dog> getAllPets() {
		return dogRrepository.findAll();
	}

	@Override
	protected Dog getPet(Integer id) {
		return dogRrepository.findOne(id);
	}
	
	@Override
	protected PetWithBreeds instantiatePetWithBreeds() {
		return new Dog();
	}
	
	@Override
	protected PetWithBreeds savePetWithBreeds(PetWithBreeds petWithBreeds) {
		return dogRrepository.save((Dog)petWithBreeds);
	}

	@Override
	protected List<? extends Breed> getBreedsFromListOfIds(List<Integer> ids, PetWithBreeds petWithBreeds) {
		List<DogBreed> breeds = dogBreedRepository.findByIdIn(ids);
		for(DogBreed dogBreed : breeds) {
			dogBreed.getDogs().add((Dog) petWithBreeds);
		}
		return breeds;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void saveBreeds(List<? extends Breed> breeds) {
		dogBreedRepository.save((List<DogBreed>)breeds);	
	}
}
