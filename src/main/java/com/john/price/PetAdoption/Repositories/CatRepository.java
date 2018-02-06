package com.john.price.PetAdoption.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.john.price.PetAdoption.Models.Cat;

@Repository("CatRepository")
public interface CatRepository extends CrudRepository<Cat, Integer> {}
