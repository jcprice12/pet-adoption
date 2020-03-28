package com.john.price.PetAdoption.Models;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "fish")
@JsonInclude(Include.NON_NULL)
public class Fish extends PetWithSpecies{}
