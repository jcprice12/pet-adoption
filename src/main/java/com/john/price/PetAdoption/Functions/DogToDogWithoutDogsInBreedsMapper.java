package com.john.price.PetAdoption.Functions;

import com.john.price.PetAdoption.Models.Dog;
import com.john.price.PetAdoption.Models.DogBreed;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DogToDogWithoutDogsInBreedsMapper implements PetToPetMapper<Dog, Dog> {

  @Autowired private DogToDogWithoutBreedsMapper dogToDogWithoutBreedsMapper;

  @Override
  public Dog apply(Dog originalDog) {
    Dog newDogWithBreeds = dogToDogWithoutBreedsMapper.apply(originalDog);

    newDogWithBreeds.setBreeds(
        originalDog.getBreeds().stream()
            .map(
                breed -> {
                  DogBreed newBreed = new DogBreed();
                  newBreed.setId(breed.getId());
                  newBreed.setName(breed.getName());
                  return newBreed;
                })
            .collect(Collectors.toSet()));

    return newDogWithBreeds;
  }
}
