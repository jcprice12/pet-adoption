package com.john.price.PetAdoption.Models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity // This tells Hibernate to make a table out of this class
public class Pet {

    private Integer petId;
    private String name;
    private Set<Breed> breeds;
    
    public Pet() {}
    
    public Pet(String name, Set<Breed> breeds) {
    	this.name = name;
    	this.breeds = breeds;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "pet_id")
	public Integer getPetId() {
		return petId;
	}

	public void setPetId(Integer petId) {
		this.petId = petId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToMany(mappedBy = "pets")
	public Set<Breed> getBreeds() {
		return this.breeds;
	}
	
	public void setBreeds(Set<Breed> breeds) {
		this.breeds = breeds;
	}
}
