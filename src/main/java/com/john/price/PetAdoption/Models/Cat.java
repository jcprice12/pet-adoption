package com.john.price.PetAdoption.Models;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "cat")
@JsonInclude(Include.NON_NULL)
public class Cat extends PetWithBreeds<CatBreed>{}
