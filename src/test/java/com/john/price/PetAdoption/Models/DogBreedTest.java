package com.john.price.PetAdoption.Models;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DogBreedTest {

    DogBreed dogBreed;

    @BeforeEach
    public void beforeEachTest() {
        dogBreed = new DogBreed();
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void test_dog_breed_is_not_equal_to_a_cat_breed() {
        CatBreed catBreed = new CatBreed();
        dogBreed.setId(1);
        catBreed.setId(1);

        boolean isEqual = dogBreed.equals(catBreed);

        assertFalse(isEqual);
    }

    @Test
    public void test_dog_breed_is_not_equal_to_null() {
        boolean isEqual = dogBreed.equals(null);

        assertFalse(isEqual);
    }
}
