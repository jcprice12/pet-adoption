package com.john.price.PetAdoption.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.john.price.PetAdoption.Models.Dog;

@Repository("DogRepository")
public interface DogRepository extends CrudRepository<Dog, Integer> {}
