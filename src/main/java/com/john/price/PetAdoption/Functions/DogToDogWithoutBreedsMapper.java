package com.john.price.PetAdoption.Functions;

import com.john.price.PetAdoption.Models.Dog;
import org.springframework.stereotype.Component;

@Component
public class DogToDogWithoutBreedsMapper implements PetToPetMapper<Dog, Dog> {

  @Override
  public Dog apply(Dog originalDog) {
    Dog newDog = new Dog();

    newDog.setId(originalDog.getId());
    newDog.setName(originalDog.getName());
    newDog.setImage(originalDog.getImage());
    newDog.setDescription(originalDog.getDescription());

    return newDog;
  }
}
