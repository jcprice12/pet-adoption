package com.john.price.PetAdoption.Services;

import com.john.price.PetAdoption.Models.PetWithSpecies;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class PetWithSpeciesService<T extends PetWithSpecies> implements PetService<T> {

  protected abstract JpaRepository<T, Integer> getRepository();

  @Override
  public Collection<T> getPets() {
    return getRepository().findAll();
  }

  @Override
  public T getPet(Integer id) {
    return getRepository().findOne(id);
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
