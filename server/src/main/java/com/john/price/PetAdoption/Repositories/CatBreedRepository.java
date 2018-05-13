package com.john.price.PetAdoption.Repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.john.price.PetAdoption.Models.CatBreed;

public interface CatBreedRepository extends CrudRepository<CatBreed, Integer> {
	
	public Set<CatBreed> findByIdIn(Set<Integer> ids);
	
	public Set<CatBreed> findByCatsId(Integer id);
	
}
