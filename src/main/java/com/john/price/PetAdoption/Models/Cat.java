package com.john.price.PetAdoption.Models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Cat extends Pet {
	private Set<CatBreed> breeds;
    
    @Column(name = "cat_id")
    public Integer getId() {
    	return super.getId();
    }
	
	@ManyToMany(mappedBy = "cats")
	public Set<CatBreed> getBreeds() {
		return breeds;
	}
	
	public void setBreeds(Set<CatBreed> breeds) {
		this.breeds = breeds;
	}
}
