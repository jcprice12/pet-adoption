package com.john.price.PetAdoption.Models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="catbreed")
public class CatBreed extends Breed {
	private Set<Cat> cats;
	
	@ManyToMany
	@JoinTable(name = "catbreed_cat",
				joinColumns = @JoinColumn(name = "breed_id"),
				inverseJoinColumns = @JoinColumn(name = "cat_id"))
	public Set<Cat> getCats(){
		return cats;
	}
	
	public void setCats(Set<Cat> cats) {
		this.cats = cats;
	}
}
