package com.john.price.PetAdoption.Functions;

import com.john.price.PetAdoption.Models.PetWithBreeds;
import java.util.function.Function;

public interface PetToPetMapper<T extends PetWithBreeds<?>, R extends PetWithBreeds<?>>
    extends Function<T, R> {}
