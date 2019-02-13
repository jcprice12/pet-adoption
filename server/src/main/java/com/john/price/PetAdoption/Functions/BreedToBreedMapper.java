package com.john.price.PetAdoption.Functions;

import java.util.function.Function;

import com.john.price.PetAdoption.Models.Breed;

public interface BreedToBreedMapper<R extends Breed> extends Function<Breed, R> {}