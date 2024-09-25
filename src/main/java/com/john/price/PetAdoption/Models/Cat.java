package com.john.price.PetAdoption.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "cat")
public class Cat extends PetWithBreeds<CatBreed> {
}
