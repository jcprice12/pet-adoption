package com.john.price.PetAdoption.Models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity // This tells Hibernate to make a table out of this class
public class Pet {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String name;
    private Set<Breed> breeds;
    
    public Pet() {
    	
    }
    
    public Pet(String name) {
    	this.name = name;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToMany(mappedBy = "breeds")
    @JoinTable
	public Set<Breed> getBreeds() {
		return this.breeds;
	}
	
	public void setBreeds(Set<Breed> breeds) {
		this.breeds = breeds;
	}
}
