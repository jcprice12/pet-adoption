package com.john.price.PetAdoption.Repositories;

import com.john.price.PetAdoption.Models.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogRepository extends JpaRepository<Dog, Integer> {}
