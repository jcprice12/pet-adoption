package com.john.price.PetAdoption.Models;

import jakarta.persistence.AssociationOverride;
import jakarta.persistence.AssociationOverrides;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Table;

@Entity
@Table(name = "catbreed")
@AssociationOverrides({
        @AssociationOverride(name = "petsWithBreeds", joinTable = @JoinTable(name = "catbreed_cat", joinColumns = @JoinColumn(name = "breed_id"), inverseJoinColumns = @JoinColumn(name = "pet_id"))) })
public class CatBreed extends Breed<Cat> {
}
