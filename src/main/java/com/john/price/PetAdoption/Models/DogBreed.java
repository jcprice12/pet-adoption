package com.john.price.PetAdoption.Models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="dogbreed")
public class DogBreed {
	
    private Integer dogBreedId;	
	private String name;
	private Set<Dog> dogs;
	
	public DogBreed() {}
	
	public DogBreed(String name) {
		this.name = name;
	}
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "dogbreed_id")
	public Integer getDogBreedId() {
		return dogBreedId;
	}

	public void setDogBreedId(Integer dogBreedId) {
		this.dogBreedId = dogBreedId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToMany
	@JoinTable(name = "dogbreed_dog",
				joinColumns = @JoinColumn(name = "dogbreed_id"),
				inverseJoinColumns = @JoinColumn(name = "dog_id"))
	public Set<Dog> getDogs(){
		return this.dogs;
	}
	
	public void setDogs(Set<Dog> dogs) {
		this.dogs = dogs;
	}
}
