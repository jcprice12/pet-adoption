package com.john.price.PetAdoption.Models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.john.price.PetAdoption.ValidatorGroups.PetPostValidation;
import com.john.price.PetAdoption.ValidatorGroups.PetPutValidation;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@MappedSuperclass
@JsonInclude(Include.NON_NULL)
public abstract class Breed<T extends PetWithBreeds<?>> {

  @NotNull private Integer id;

  @NotNull private String name;

  @Null(groups = {PetPostValidation.class, PetPutValidation.class})
  private Set<T> petsWithBreeds;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "breed_id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Column(nullable = false, unique = true)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @ManyToMany(
      cascade = {CascadeType.PERSIST},
      fetch = FetchType.LAZY)
  public Set<T> getPetsWithBreeds() {
    return this.petsWithBreeds;
  }

  public void setPetsWithBreeds(Set<T> petsWithBreeds) {
    this.petsWithBreeds = petsWithBreeds;
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Breed<?>)) {
      return false;
    } else if (o.getClass() != this.getClass()) {
      return false;
    } else if (o == this) {
      return true;
    }

    Breed<T> breed = (Breed<T>) o;

    return new EqualsBuilder().append(id, breed.id).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(id).toHashCode();
  }
}
