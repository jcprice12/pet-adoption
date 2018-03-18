package com.john.price.PetAdoption.Models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Cat extends PetWithBreeds{ 
	
	public Cat() {}
	
	public Cat(String name, String image, String description) {
		super(name, image, description);
	}
	
	public Cat(Integer id, String name, String image, String description) {
		super(name, image, description);
	}
	
	public Cat(String name, String image, String description, Set<Breed> breeds) {
		super(name, image, description, breeds);
	}
	
	public Cat(Integer id, String name, String image, String description, Set<Breed> breeds) {
		super(id, name, image, description, breeds);
	}
	
    @Column(name = "cat_id")
    public Integer getId() {
    	return super.getId();
    }
	
    @Override
	@ManyToMany(targetEntity=CatBreed.class, mappedBy="cats")
	public Set<Breed> getBreeds() {
		return breeds;
	}	
    @Override
	public void setBreeds(Set<Breed> breeds) {
		this.breeds = breeds;
	}
}
