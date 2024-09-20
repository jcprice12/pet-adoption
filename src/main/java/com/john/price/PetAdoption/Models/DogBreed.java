package com.john.price.PetAdoption.Models;

import jakarta.persistence.AssociationOverride;
import jakarta.persistence.AssociationOverrides;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Table;

@Entity
@Table(name = "dogbreed")
@AssociationOverrides({
        @AssociationOverride(name = "petsWithBreeds", joinTable = @JoinTable(name = "dogbreed_dog", joinColumns = @JoinColumn(name = "breed_id"), inverseJoinColumns = @JoinColumn(name = "pet_id"))) })
public class DogBreed extends Breed<Dog> {
}
