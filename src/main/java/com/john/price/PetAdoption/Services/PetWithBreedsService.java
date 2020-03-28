package com.john.price.PetAdoption.Services;

import com.john.price.PetAdoption.Functions.PetToPetMapper;
import com.john.price.PetAdoption.Models.Breed;
import com.john.price.PetAdoption.Models.PetWithBreeds;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.function.BiFunction;
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
    return savePet(
        p,
        new BiFunction<P, List<B>, P>() {

          @Override
          public P apply(P newPet, List<B> breeds) {
            newPet.setBreeds(new HashSet<>(breeds));
            P savedPet = getPetRepository().save(newPet);
            getBreedRepository().saveAll(breeds);
            return savedPet;
          }
        });
  }

  @Override
  public P editPet(P p) {
    return savePet(
        p,
        new BiFunction<P, List<B>, P>() {

          @Override
          public P apply(P newPet, List<B> breeds) {
            getBreedRepository().saveAll(breeds);
            newPet.setBreeds(new HashSet<>(breeds));
            return getPetRepository().save(newPet);
          }
        });
  }

  private P savePet(P p, BiFunction<P, List<B>, P> function) {
    P newPet = getPetWithoutBreedsMapper().apply(p);
    List<B> breeds =
        getBreedRepository()
            .findAllById(p.getBreeds().stream().map(Breed::getId).collect(Collectors.toList()));
    breeds.forEach(
        breed -> {
          breed.getPetsWithBreeds().add(newPet);
        });

    return getApiPetMapper().apply(function.apply(newPet, breeds));
  }
}
