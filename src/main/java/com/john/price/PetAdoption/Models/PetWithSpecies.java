package com.john.price.PetAdoption.Models;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.Valid;

@MappedSuperclass
public abstract class PetWithSpecies<S> extends Pet {

  @Valid private S species;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "species_id")
  public S getSpecies() {
    return this.species;
  }

  public void setSpecies(S species) {
    this.species = species;
  }
}
