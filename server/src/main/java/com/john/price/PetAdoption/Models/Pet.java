package com.john.price.PetAdoption.Models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@MappedSuperclass
public abstract class Pet {
	protected Integer id;
	protected String name;
	protected String image;
	protected String description;
	
	public Pet() {}
	
	public Pet(Integer id, String name, String image, String description) {
		this.id = id;
		this.name = name;
		this.image = image;
		this.description = description;
	}
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
    public boolean equals(Object o) {
    	if(!(o instanceof Pet)) {
    		return false;
    	} else if (o == this) {
    		return true;
    	} 
    	
    	Pet pet = (Pet)o;
    	
    	return new EqualsBuilder()
    			.append(id,pet.id)
    			.isEquals();
    }
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17,37)
				.append(id)
				.toHashCode();
	}
}
