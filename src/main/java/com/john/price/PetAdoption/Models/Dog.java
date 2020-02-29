package com.john.price.PetAdoption.Models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
@JsonInclude(Include.NON_NULL)
public class Dog extends PetWithBreeds {

  public Dog() {}

  public Dog(Dog dog) {
    super(dog);
  }

  @Column(name = "dog_id")
  public Integer getId() {
    return super.getId();
  }

  @Override
  @ManyToMany(targetEntity = DogBreed.class, mappedBy = "dogs")
  public Set<Breed> getBreeds() {
    return breeds;
  }

  @Override
  public void setBreeds(Set<Breed> breeds) {
    this.breeds = breeds;
  }
}