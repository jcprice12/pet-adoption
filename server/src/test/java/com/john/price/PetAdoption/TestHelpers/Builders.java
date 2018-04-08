package com.john.price.PetAdoption.TestHelpers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.john.price.PetAdoption.Models.Breed;
import com.john.price.PetAdoption.Models.Cat;
import com.john.price.PetAdoption.Models.CatBreed;
import com.john.price.PetAdoption.Models.Dog;
import com.john.price.PetAdoption.Models.DogBreed;
import com.john.price.PetAdoption.Responses.PetWithBreedsResponse;

public abstract class Builders {
	
	public static CatBreed buildCatBreedWithoutCats(){
		return new CatBreed(Constants.ONE_ID, "American Shorthair");
	}
	
	public static DogBreed buildDogBreedWithoutDogs() {
		return new DogBreed(Constants.ONE_ID, "Labrador Retriever");
	}
	
	public static List<CatBreed> buildCatBreedsWithoutCats() {
		List<CatBreed> catBreeds = new ArrayList<CatBreed>();
		catBreeds.add(buildCatBreedWithoutCats());
		return catBreeds;
	}
	
	public static List<DogBreed> buildDogBreedsWithoutDogs() {
		List<DogBreed> dogBreeds = new ArrayList<DogBreed>();
		dogBreeds.add(buildDogBreedWithoutDogs());
		return dogBreeds;
	}
	
	public static Cat buildPlainCat() {
		CatBreed catBreed = buildCatBreedWithoutCats();
		Cat cat = new Cat(Constants.ONE_ID, "Daisy", "cat.jpg", "a nice cat");
		
		Set<Breed> breeds = new HashSet<Breed>();
		Set<Cat> cats = new HashSet<Cat>();
		
		cats.add(cat);
		breeds.add(catBreed);
		
		catBreed.setCats(cats);
		cat.setBreeds(breeds);
		
		return cat;
	}
	
	public static Dog buildPlainDog() {
		DogBreed dogBreed = buildDogBreedWithoutDogs();
		Dog dog = new Dog(Constants.ONE_ID, "Labby", "dog.jpg", "a nice dog");
		
		Set<Breed> breeds = new HashSet<Breed>();
		Set<Dog> dogs = new HashSet<Dog>();
		
		dogs.add(dog);
		breeds.add(dogBreed);
		
		dogBreed.setDogs(dogs);
		dog.setBreeds(breeds);
		
		return dog;
	}
	
	public static PetWithBreedsResponse buildPlainDogResponse() {
		return new PetWithBreedsResponse(buildPlainDog());
	}
	
	public static PetWithBreedsResponse buildPainCatResponse() {
		return new PetWithBreedsResponse(buildPlainCat());
	}
	
	public static Iterable<PetWithBreedsResponse> buildDogsResponse() {
		List<PetWithBreedsResponse> pets = new ArrayList<PetWithBreedsResponse>();
		pets.add(buildPlainDogResponse());
		return pets;
	}
	
	public static Iterable<PetWithBreedsResponse> buildCatsResponse() {
		List<PetWithBreedsResponse> pets = new ArrayList<PetWithBreedsResponse>();
		pets.add(buildPainCatResponse());
		return pets;
	}
}
