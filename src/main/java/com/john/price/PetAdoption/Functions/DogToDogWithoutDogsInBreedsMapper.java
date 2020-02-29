package com.john.price.PetAdoption.Functions;

import com.john.price.PetAdoption.Models.Dog;
import org.springframework.stereotype.Component;

@Component
public class DogToDogWithoutDogsInBreedsMapper implements PetToPetMapper<Dog, Dog> {

  @Override
  public Dog apply(Dog dog) {
    return new Dog(dog);
  }
}
