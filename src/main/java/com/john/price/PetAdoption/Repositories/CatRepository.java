package com.john.price.PetAdoption.Repositories;

import com.john.price.PetAdoption.Models.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepository extends JpaRepository<Cat, Integer> {}
