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

@Entity
public class Breed {
	
    private Integer breedId;	
	private String name;
	private Set<Pet> pets;
	
	public Breed() {}
	
	public Breed(String name) {
		this.name = name;
	}
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "breed_id")
	public Integer getBreedId() {
		return breedId;
	}

	public void setBreedId(Integer breedId) {
		this.breedId = breedId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToMany
	@JoinTable(name = "breed_pet",
				joinColumns = @JoinColumn(name = "breed_id"),
				inverseJoinColumns = @JoinColumn(name = "pet_id"))
	public Set<Pet> getPets(){
		return this.pets;
	}
	
	public void setPets(Set<Pet> pets) {
		this.pets = pets;
	}
}
