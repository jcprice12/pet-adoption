package com.john.price.PetAdoption.Models;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "dogbreed")
@AssociationOverrides({
		@AssociationOverride(name = "petsWithBreeds", joinTable = @JoinTable(name = "dogbreed_dog", joinColumns = @JoinColumn(name = "breed_id"), inverseJoinColumns = @JoinColumn(name = "pet_id"))) })
@JsonInclude(Include.NON_NULL)
public class DogBreed extends Breed<Dog> {}
