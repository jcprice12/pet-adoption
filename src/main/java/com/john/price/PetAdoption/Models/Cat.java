package com.john.price.PetAdoption.Models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cat")
public class Cat extends PetWithBreeds<CatBreed> {}
