package com.john.price.PetAdoption.Models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "dogbreed")
@JsonInclude(Include.NON_NULL)
public class DogBreed extends Breed {

  private Set<Dog> dogs;

  public DogBreed() {}

  @ManyToMany
  @JoinTable(
      name = "dogbreed_dog",
      joinColumns = @JoinColumn(name = "breed_id"),
      inverseJoinColumns = @JoinColumn(name = "dog_id"))
  public Set<Dog> getDogs() {
    return this.dogs;
  }

  public void setDogs(Set<Dog> dogs) {
    this.dogs = dogs;
  }
}
