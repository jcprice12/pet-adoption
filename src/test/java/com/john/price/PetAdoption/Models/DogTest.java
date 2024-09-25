package com.john.price.PetAdoption.Models;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DogTest {

    Dog dog;

    @BeforeEach
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
