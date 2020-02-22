package com.john.price.PetAdoption.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.john.price.PetAdoption.Models.Dog;

public interface DogRepository extends JpaRepository<Dog, Integer> {}