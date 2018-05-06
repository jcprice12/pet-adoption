package com.john.price.PetAdoption.TestHelpers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.john.price.PetAdoption.Responses.PetWithBreedsResponse;
import com.john.price.PetAdoption.Models.Breed;
import com.john.price.PetAdoption.Models.Cat;
import com.john.price.PetAdoption.Models.CatBreed;
import com.john.price.PetAdoption.Models.Dog;
import com.john.price.PetAdoption.Models.DogBreed;
import com.john.price.PetAdoption.Models.PetWithBreeds;

public abstract class Builders {
	
	public static CatBreed buildCatBreedWithoutCats(){
		CatBreed catBreed = new CatBreed(Constants.ONE_ID, "American Shorthair");
		catBreed.setCats(new HashSet<Cat>());
		return catBreed;
	}
	
	public static DogBreed buildDogBreedWithoutDogs() {
		DogBreed dogBreed = new DogBreed(Constants.ONE_ID, "Labrador Retriever");
		dogBreed.setDogs(new HashSet<Dog>());
		return dogBreed;
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
	
	public static CatBreed buildCatBreedWithCats() {
		CatBreed catBreed = buildCatBreedWithoutCats();
		catBreed.setCats(buildCatsWithBreedsWithoutCats());
		return catBreed;
	}
	
	public static DogBreed buildDogBreedWithDogs() {
		DogBreed dogBreed = buildDogBreedWithoutDogs();
		dogBreed.setDogs(buildDogsWithBreedsWithoutDogs());
		return dogBreed;
	}
	
	public static Set<Breed> buildCatBreedsWithCats() {
		Set<Breed> catBreeds = new HashSet<Breed>();
		catBreeds.add(buildCatBreedWithCats());
		return catBreeds;
	}
	
	public static Set<Breed> buildDogBreedsWithDogs() {
		Set<Breed> dogBreeds = new HashSet<Breed>();
		dogBreeds.add(buildDogBreedWithDogs());
		return dogBreeds;
	}
	
	public static Cat buildCatWithBreedsWithoutCats() {
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
	
	public static Set<Cat> buildCatsWithBreedsWithoutCats() {
		Set<Cat> cats = new HashSet<Cat>();
		cats.add(buildCatWithBreedsWithoutCats());
		return cats;
	}
	
	public static Dog buildDogWithBreedsWithoutDogs() {
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
	
	public static Set<Dog> buildDogsWithBreedsWithoutDogs() {
		Set<Dog> dogs = new HashSet<Dog>();
		dogs.add(buildDogWithBreedsWithoutDogs());
		return dogs;
	}
	
	public static Cat buildCatWithBreedsWithCats() {
		Cat cat = buildCatWithBreedsWithoutCats();
		cat.setBreeds(buildCatBreedsWithCats());
		return cat;
	}
	
	public static Dog buildDogWithBreedsWithDogs() {
		Dog dog = buildDogWithBreedsWithoutDogs();
		dog.setBreeds(buildDogBreedsWithDogs());
		return dog;
	}
	
	public static PetWithBreeds buildPlainDogResponse() {
		return new PetWithBreedsResponse(buildDogWithBreedsWithoutDogs());
	}
	
	public static PetWithBreeds buildPainCatResponse() {
		return new PetWithBreedsResponse(buildCatWithBreedsWithoutCats());
	}
	
	public static Iterable<PetWithBreeds> buildDogsResponse() {
		List<PetWithBreedsResponse> pets = new ArrayList<PetWithBreedsResponse>();
		pets.add(buildPlainDogResponse());
		return pets;
	}
	
	public static Iterable<PetWithBreeds> buildCatsResponse() {
		List<PetWithBreedsResponse> pets = new ArrayList<PetWithBreedsResponse>();
		pets.add(buildPainCatResponse());
		return pets;
	}
}
