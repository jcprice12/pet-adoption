package com.john.price.PetAdoption.Models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "fish")
public class Fish extends PetWithSpecies<FishSpecies> {}
