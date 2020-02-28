package com.john.price.PetAdoption.Functions;

import com.john.price.PetAdoption.Models.Cat;
import org.springframework.stereotype.Component;

@Component
public class CatToCatWithoutCatsInBreedsMapper implements PetToPetMapper<Cat, Cat> {

  @Override
  public Cat apply(Cat cat) {
    return new Cat(cat);
  }
}
