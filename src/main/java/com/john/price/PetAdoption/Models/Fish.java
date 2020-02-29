package com.john.price.PetAdoption.Models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@JsonInclude(Include.NON_NULL)
public class Fish extends PetWithSpecies {

  @Override
  @ManyToOne(targetEntity = FishSpecies.class)
  @JoinColumn(name = "species_id")
  public Species getSpecies() {
    return this.species;
  }

  @Override
  public void setSpecies(Species species) {
    this.species = species;
  }
}
