package com.john.price.PetAdoption.Services;

import java.util.Iterator;
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
	protected List<? extends PetWithBreeds> getAllPets() {
		return (List<Cat>)catRepository.findAll();
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
	protected List<? extends Breed> getBreedsFromListOfIds(List<Integer> ids, PetWithBreeds petWithBreeds) {
		return catBreedRepository.findByIdIn(ids);
	}
	
	@Override
	protected List<? extends Breed> getBreedsThatHavePetWithBreeds(Integer id) {
		return catBreedRepository.findByCatsId(id);
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

	@Override
	protected void removePetWithBreedsFromBreed(Integer petWithBreedsId, Breed breed) {
		CatBreed catBreed = (CatBreed) breed;
		Iterator<Cat> iterator = catBreed.getCats().iterator();
		while(iterator.hasNext()) {
			Cat cat = iterator.next();
			if(cat.getId() == petWithBreedsId) {
				iterator.remove();
				break;
			}
		}
	}

	@Override
	protected void nullifyPetsInBreed(Breed breed) {
		CatBreed catBreed = (CatBreed) breed;
		catBreed.setCats(null);
	}
}
