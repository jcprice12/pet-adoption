package com.john.price.PetAdoption.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.john.price.PetAdoption.Models.CatBreed;

public interface CatBreedRepository extends CrudRepository<CatBreed, Integer> {
	
	public List<CatBreed> findByIdIn(List<Integer> ids);
	
	public List<CatBreed> findByCatsId(Integer id);
	
}
