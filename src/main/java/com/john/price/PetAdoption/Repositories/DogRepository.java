package com.john.price.PetAdoption.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.john.price.PetAdoption.Models.Dog;

public interface DogRepository extends CrudRepository<Dog, Integer> {}
