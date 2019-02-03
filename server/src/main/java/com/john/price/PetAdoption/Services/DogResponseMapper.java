package com.john.price.PetAdoption.Services;

import java.util.Iterator;
import java.util.Set;

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
	private DogRepository dogRepository;
	
	@Autowired
	private DogBreedRepository dogBreedRepository;

	@Override
	protected Iterable<? extends PetWithBreeds> getAllPets() {
		return dogRepository.findAll();
	}

	@Override
	protected Dog getPet(Integer id) {
		return dogRepository.findOne(id);
	}
	
	@Override
	protected PetWithBreeds savePetWithBreeds(PetWithBreeds petWithBreeds) {
		return dogRepository.save((Dog)petWithBreeds);
	}

	@Override
	protected Set<? extends Breed> getBreedsFromListOfIds(Set<Integer> ids, PetWithBreeds petWithBreeds) {
		return dogBreedRepository.findByIdIn(ids);
	}
	
	@Override
	protected Set<? extends Breed> getBreedsThatHavePetWithBreeds(Integer id) {
		return dogBreedRepository.findByDogsId(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void saveBreeds(Set<? extends Breed> breeds) {
		dogBreedRepository.save((Set<DogBreed>)breeds);	
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

	@Override
	protected void nullifyPetsInBreed(Breed breed) {
		DogBreed dogBreed = (DogBreed) breed;
		dogBreed.setDogs(null);
	}
}
