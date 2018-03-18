package com.john.price.PetAdoption.Models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="dogbreed")
public class DogBreed extends Breed{
	
	private Set<Dog> dogs;
	
	public DogBreed() {}
	
	public DogBreed(String name) {
		super(name);
	}
	
	public DogBreed(Integer id, String name) {
		super(id, name);
	}
	
	public DogBreed(String name, Set<Dog> dogs) {
		super(name);
		this.dogs = dogs;
	}
	
	public DogBreed(Integer id, String name, Set<Dog> dogs) {
		super(id, name);
		this.dogs = dogs;
	}
	
	@ManyToMany
	@JoinTable(name = "dogbreed_dog",
				joinColumns = @JoinColumn(name = "breed_id"),
				inverseJoinColumns = @JoinColumn(name = "dog_id"))
	@NotNull
	public Set<Dog> getDogs(){
		return this.dogs;
	}
	
	public void setDogs(Set<Dog> dogs) {
		this.dogs = dogs;
	}
}
