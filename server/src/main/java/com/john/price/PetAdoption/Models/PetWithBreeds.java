package com.john.price.PetAdoption.Models;

import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public abstract class PetWithBreeds extends Pet {

  @NotNull @Valid protected Set<Breed> breeds;

  public abstract Set<Breed> getBreeds();

  public abstract void setBreeds(Set<Breed> breeds);

  public PetWithBreeds() {}

  public PetWithBreeds(PetWithBreeds petWithBreeds) {
    this.id = petWithBreeds.id;
    this.description = petWithBreeds.description;
    this.name = petWithBreeds.name;
    this.image = petWithBreeds.image;
    this.breeds =
        petWithBreeds.getBreeds().stream()
            .map(breed -> new Breed(breed))
            .collect(Collectors.toSet());
  }
}
