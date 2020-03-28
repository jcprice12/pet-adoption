package com.john.price.PetAdoption.Services;

import com.john.price.PetAdoption.Functions.PetToPetMapper;
import com.john.price.PetAdoption.Models.Breed;
import com.john.price.PetAdoption.Models.PetWithBreeds;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class PetWithBreedsService<P extends PetWithBreeds<B>, B extends Breed<P>>
    implements PetService<P> {

  protected abstract PetToPetMapper<P, P> getApiPetMapper();

  protected abstract JpaRepository<P, Integer> getPetRepository();

  protected abstract JpaRepository<B, Integer> getBreedRepository();

  protected abstract PetToPetMapper<P, P> getPetWithoutBreedsMapper();

  @Override
  public Collection<P> getPets() {
    return getPetRepository().findAll().stream()
        .map(getApiPetMapper())
        .collect(Collectors.toList());
  }

  @Override
  public P getPet(Integer id) {
    return getApiPetMapper().apply(getPetRepository().findById(id).get());
  }

  @Override
  public P createPet(P p) {
    P newPet = getPetWithoutBreedsMapper().apply(p);
    List<B> breeds =
        getBreedRepository()
            .findAllById(p.getBreeds().stream().map(Breed::getId).collect(Collectors.toList()));
    breeds.forEach(
        breed -> {
          breed.getPetsWithBreeds().add(newPet);
        });
    newPet.setBreeds(new HashSet<>(breeds));
    P savedPet = getPetRepository().save(newPet);
    getBreedRepository().saveAll(breeds);
    return getApiPetMapper().apply(savedPet);
  }

  @Override
  public P editPet(P p) {
    P newPet = getPetWithoutBreedsMapper().apply(p);
    List<B> breeds =
        getBreedRepository()
            .findAllById(p.getBreeds().stream().map(Breed::getId).collect(Collectors.toList()));
    breeds.forEach(
        breed -> {
          breed.getPetsWithBreeds().add(newPet);
        });
    getBreedRepository().saveAll(breeds);
    newPet.setBreeds(new HashSet<>(breeds));
    P savedPet = getPetRepository().save(newPet);
    return getApiPetMapper().apply(savedPet);
  }
}
