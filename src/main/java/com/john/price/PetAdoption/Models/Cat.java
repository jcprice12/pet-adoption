package com.john.price.PetAdoption.Models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cat")
@JsonInclude(Include.NON_NULL)
public class Cat extends PetWithBreeds<CatBreed> {}
