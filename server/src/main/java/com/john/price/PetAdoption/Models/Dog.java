package com.john.price.PetAdoption.Models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Dog extends PetWithBreeds{  
	
	public Dog() {}
	
	public Dog(Integer id, String name, String image, String description) {
		super(id, name, image, description);
	}
	
    @Column(name = "dog_id")
    public Integer getId() {
    	return super.getId();
    }	
    
    @Override
	@ManyToMany(targetEntity=DogBreed.class, mappedBy="dogs")
	public Set<Breed> getBreeds() {
		return breeds;
	}
    @Override
	public void setBreeds(Set<Breed> breeds) {
		this.breeds = breeds;
	}
}
