package com.john.price.PetAdoption.Services;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.john.price.PetAdoption.Models.Pet;

public abstract class PetWithSpeciesService<T extends Pet> implements PetService<T> {

  protected abstract JpaRepository<T, Integer> getRepository();

  @Override
  public Collection<T> getPets() {
    return getRepository().findAll();
  }

  @Override
  public T getPet(Integer id) {
    return getRepository().findById(id).get();
  }

  @Override
  public T createPet(T t) {
    return getRepository().save(t);
  }

  @Override
  public T editPet(T t) {
    return getRepository().save(t);
  }
}
