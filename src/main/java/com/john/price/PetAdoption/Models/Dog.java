package com.john.price.PetAdoption.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "dog")
public class Dog extends PetWithBreeds<DogBreed> {
}
