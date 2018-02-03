package com.john.price.PetAdoption.Models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity // This tells Hibernate to make a table out of this class
public class Dog {

    private Integer dogId;
    private String name;
    private Set<DogBreed> dogBreeds;
    
    public Dog() {}
    
    public Dog(String name, Set<DogBreed> dogBreeds) {
    	this.name = name;
    	this.dogBreeds = dogBreeds;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "dog_id")
	public Integer getDogId() {
		return dogId;
	}

	public void setDogId(Integer dogId) {
		this.dogId = dogId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToMany(mappedBy = "dogs")
	public Set<DogBreed> getDogBreeds() {
		return this.dogBreeds;
	}
	
	public void setDogBreeds(Set<DogBreed> dogBreeds) {
		this.dogBreeds = dogBreeds;
	}
}
