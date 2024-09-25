package com.john.price.PetAdoption.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "fish")
public class Fish extends PetWithSpecies<FishSpecies> {
}
