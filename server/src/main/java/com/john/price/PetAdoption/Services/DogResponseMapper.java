package com.john.price.PetAdoption.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.john.price.PetAdoption.Models.Breed;
import com.john.price.PetAdoption.Models.Dog;
import com.john.price.PetAdoption.Models.DogBreed;
import com.john.price.PetAdoption.Models.PetWithBreeds;
import com.john.price.PetAdoption.Repositories.DogRepository;

@Component("DogResponseMapper")
public class DogResponseMapper extends PetWithBreedsResponseMapper{
	
	@Autowired
	private DogRepository dogRepository;

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
	protected String getIntersectionTableInsertionString() {
		return "INSERT INTO dogbreed_dog (breed_id, dog_id) VALUES (?, ?)";
	}

	@Override
	protected String getIntersectionTableDeletionString() {
		return "DELETE from dogbreed_dog where dog_id = ?";
	}
	
	@Override
	protected Breed mapBreed(Breed breed) {
		DogBreed dogBreed = new DogBreed();
		dogBreed.setDogs(null);
		dogBreed.setId(breed.getId());
		dogBreed.setName(breed.getName());
		return dogBreed;
	}
}
