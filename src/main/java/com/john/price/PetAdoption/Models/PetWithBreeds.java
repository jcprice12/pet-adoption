package com.john.price.PetAdoption.Models;

import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@MappedSuperclass
public abstract class PetWithBreeds<T extends Breed<?>> extends Pet {

    @Valid
    @NotNull
    @Size(min = 1, max = 5)
    private Set<T> breeds;

    @ManyToMany(mappedBy = "petsWithBreeds", cascade = { CascadeType.PERSIST }, fetch = FetchType.LAZY)
    public Set<T> getBreeds() {
        return breeds;
    }

    public void setBreeds(Set<T> breeds) {
        this.breeds = breeds;
    }
}
