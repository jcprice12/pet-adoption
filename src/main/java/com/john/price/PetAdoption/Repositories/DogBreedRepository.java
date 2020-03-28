package com.john.price.PetAdoption.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.john.price.PetAdoption.Models.DogBreed;

public interface DogBreedRepository extends JpaRepository<DogBreed, Integer>{}
