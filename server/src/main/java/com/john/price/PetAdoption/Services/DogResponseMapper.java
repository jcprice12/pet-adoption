package com.john.price.PetAdoption.Services;

import java.util.Iterator;
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
		return dogBreedRepository.findByIdIn(ids);
	}
	
	@Override
	protected List<? extends Breed> getBreedsThatHavePetWithBreeds(Integer id) {
		return dogBreedRepository.findByDogsId(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void saveBreeds(List<? extends Breed> breeds) {
		dogBreedRepository.save((List<DogBreed>)breeds);	
	}

	@Override
	protected Breed addPetWithBreedsToBreed(PetWithBreeds petWithBreeds, Breed breed) {
		DogBreed dogBreed = (DogBreed) breed;
		dogBreed.getDogs().add((Dog)petWithBreeds);
		return dogBreed;
	}

	@Override
	protected void removePetWithBreedsFromBreed(Integer petWithBreedsId, Breed breed) {
		DogBreed dogBreed = (DogBreed) breed;
		Iterator<Dog> iterator = dogBreed.getDogs().iterator();
		while(iterator.hasNext()) {
			Dog dog = iterator.next();
			if(dog.getId() == petWithBreedsId) {
				iterator.remove();
				break;
			}
		}
	}
}
