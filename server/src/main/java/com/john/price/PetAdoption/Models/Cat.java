package com.john.price.PetAdoption.Models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@JsonInclude(Include.NON_NULL)
public class Cat extends PetWithBreeds{ 
	
	public Cat() {}
	
	public Cat(Cat cat) {
		super(cat);
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
