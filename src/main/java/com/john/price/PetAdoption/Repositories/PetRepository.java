package com.john.price.PetAdoption.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.john.price.PetAdoption.Models.Pet;

public interface PetRepository extends CrudRepository<Pet, Integer> {
	
}
