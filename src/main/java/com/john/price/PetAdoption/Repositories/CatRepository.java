package com.john.price.PetAdoption.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.john.price.PetAdoption.Models.Cat;

public interface CatRepository extends CrudRepository<Cat, Integer> {}
