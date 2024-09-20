package com.john.price.PetAdoption.Models;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@MappedSuperclass
public abstract class PetWithSpecies<S> extends Pet {

    @Valid
    @NotNull
    private S species;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "species_id")
    public S getSpecies() {
        return this.species;
    }

    public void setSpecies(S species) {
        this.species = species;
    }
}
