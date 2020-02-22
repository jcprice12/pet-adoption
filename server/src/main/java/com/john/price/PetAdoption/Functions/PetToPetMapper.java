package com.john.price.PetAdoption.Functions;

import java.util.function.Function;

import com.john.price.PetAdoption.Models.PetWithBreeds;

public interface PetToPetMapper<T extends PetWithBreeds, R extends PetWithBreeds> extends Function<T, R> {}