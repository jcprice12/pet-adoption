package com.john.price.PetAdoption.Models;

import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

public class DogTest {

  Dog dog;

  @Before
  public void beforeEachTest() {
    dog = new Dog();
  }

  @SuppressWarnings("unlikely-arg-type")
  @Test
  public void test_dog_is_not_equal_to_cat() {
    Cat cat = new Cat();
    cat.setId(1);
    dog.setId(1);

    boolean isEqual = dog.equals(cat);

    assertFalse(isEqual);
  }

  @Test
  public void test_dog_is_not_equal_to_null() {
    boolean isEqual = dog.equals(null);

    assertFalse(isEqual);
  }
}
