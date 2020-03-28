package com.john.price.PetAdoption.Models;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class PetWithBreeds<T extends Breed<?>> extends Pet {

  private Set<T> breeds;

  @ManyToMany(
      mappedBy = "petsWithBreeds",
      cascade = {CascadeType.PERSIST},
      fetch = FetchType.LAZY)
  public Set<T> getBreeds() {
    return breeds;
  }

  public void setBreeds(Set<T> breeds) {
    this.breeds = breeds;
  }
}
