package com.john.price.PetAdoption.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.john.price.PetAdoption.Models.Cat;

public interface CatRepository extends JpaRepository<Cat, Integer> {}