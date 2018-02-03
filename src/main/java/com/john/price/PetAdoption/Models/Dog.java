package com.john.price.PetAdoption.Models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Dog extends Pet{

    private Set<DogBreed> breeds;
    
    public Dog() {}
    
    public Dog(String name, String image, Set<DogBreed> breeds) {
    	this.name = name;
    	this.image = image;
    	this.breeds = breeds;
    }
    
    @Column(name = "dog_id")
    public Integer getId() {
    	return super.getId();
    }
	
	@ManyToMany(mappedBy = "dogs")
	public Set<DogBreed> getBreeds() {
		return breeds;
	}
	
	public void setBreeds(Set<DogBreed> breeds) {
		this.breeds = breeds;
	}
}
