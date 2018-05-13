package com.john.price.PetAdoption.Repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.john.price.PetAdoption.Models.DogBreed;

public interface DogBreedRepository extends CrudRepository<DogBreed, Integer> {

	public Set<DogBreed> findByIdIn(Set<Integer> ids);
	
	public Set<DogBreed> findByDogsId(Integer id);
	
}
