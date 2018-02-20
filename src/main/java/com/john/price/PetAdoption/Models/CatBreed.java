package com.john.price.PetAdoption.Models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="catbreed")
public class CatBreed extends Breed {
	private Set<Cat> cats;
	
	public CatBreed() {}
	
	public CatBreed(String name, Set<Cat> cats){
		super(name);
		this.cats = cats;
	}
	
	public CatBreed(Integer id, String name, Set<Cat> cats) {
		super(id, name);
		this.cats = cats;
	}
	
	@ManyToMany
	@JoinTable(name = "catbreed_cat",
				joinColumns = @JoinColumn(name = "breed_id"),
				inverseJoinColumns = @JoinColumn(name = "cat_id"))
	@NotNull
	public Set<Cat> getCats(){
		return cats;
	}
	
	public void setCats(Set<Cat> cats) {
		this.cats = cats;
	}
}
