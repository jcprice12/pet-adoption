package com.john.price.PetAdoption.Models;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class PetWithSpecies extends Pet {
  private Species species;

  @ManyToOne(targetEntity = FishSpecies.class)
  @JoinColumn(name = "species_id")
  public Species getSpecies() {
    return this.species;
  }

  public void setSpecies(Species species) {
    this.species = species;
  }
}
