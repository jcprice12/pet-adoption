package com.john.price.PetAdoption.Models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "dog")
public class Dog extends PetWithBreeds<DogBreed> {}
