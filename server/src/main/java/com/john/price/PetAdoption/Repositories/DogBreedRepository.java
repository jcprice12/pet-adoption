package com.john.price.PetAdoption.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.john.price.PetAdoption.Models.DogBreed;

public interface DogBreedRepository extends CrudRepository<DogBreed, Integer> {

	public List<DogBreed> findByIdIn(List<Integer> ids);
	
}
