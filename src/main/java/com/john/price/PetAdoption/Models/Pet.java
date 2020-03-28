package com.john.price.PetAdoption.Models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.john.price.PetAdoption.ValidatorGroups.PetPostValidation;
import com.john.price.PetAdoption.ValidatorGroups.PetPutValidation;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@MappedSuperclass
@JsonInclude(Include.NON_NULL)
public abstract class Pet {
  @NotNull(groups = {PetPutValidation.class})
  @Null(groups = {PetPostValidation.class})
  private Integer id;

  @NotNull private String name;
  private String image;
  private String description;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "pet_id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Column(nullable = false)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Pet)) {
      return false;
    } else if (o.getClass() != this.getClass()) {
      return false;
    } else if (o == this) {
      return true;
    }

    Pet pet = (Pet) o;

    return new EqualsBuilder().append(id, pet.id).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(id).toHashCode();
  }
}
