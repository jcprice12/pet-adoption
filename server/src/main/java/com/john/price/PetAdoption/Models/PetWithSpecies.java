package com.john.price.PetAdoption.Models;

public abstract class PetWithSpecies extends Pet {

  protected Species species;

  public abstract Species getSpecies();

  public abstract void setSpecies(Species species);

  public PetWithSpecies() {}
}
