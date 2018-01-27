package com.john.price.PetAdoption.Models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Breed {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;	
	private String name;
	private Set<Pet> pets;
	
	public Breed() {}
	
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
	
	public Set<Pet> getPets(){
		return this.pets;
	}
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable
	public void setPets(Set<Pet> pets) {
		this.pets = pets;
	}
}
