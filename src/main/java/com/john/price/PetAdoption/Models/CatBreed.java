package com.john.price.PetAdoption.Models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

@Entity
@Table(name = "catbreed")
@AssociationOverrides({
  @AssociationOverride(
      name = "petsWithBreeds",
      joinTable =
          @JoinTable(
              name = "catbreed_cat",
              joinColumns = @JoinColumn(name = "breed_id"),
              inverseJoinColumns = @JoinColumn(name = "pet_id")))
})
@JsonInclude(Include.NON_NULL)
public class CatBreed extends Breed<Cat> {}
