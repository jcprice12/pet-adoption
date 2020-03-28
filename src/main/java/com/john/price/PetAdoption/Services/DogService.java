package com.john.price.PetAdoption.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.john.price.PetAdoption.Exceptions.UploadS3FileException;
import com.john.price.PetAdoption.Functions.DogToDogWithoutBreedsMapper;
import com.john.price.PetAdoption.Functions.DogToDogWithoutDogsInBreedsMapper;
import com.john.price.PetAdoption.Functions.PetToPetMapper;
import com.john.price.PetAdoption.Models.Dog;
import com.john.price.PetAdoption.Models.DogBreed;
import com.john.price.PetAdoption.Repositories.DogBreedRepository;
import com.john.price.PetAdoption.Repositories.DogRepository;

@Component
public class DogService extends PetWithBreedsService<Dog, DogBreed> {

	@Autowired
	private DogToDogWithoutDogsInBreedsMapper dogToDogWithoutDogsInBreedsMapper;
	
	@Autowired
	private DogToDogWithoutBreedsMapper dogToDogWithoutBreedsMapper;

	@Autowired
	private DogRepository dogRepository;

	@Autowired
	private DogBreedRepository dogBreedRepository;

	@Autowired
	private S3Service s3Service;

	@Override
	protected PetToPetMapper<Dog, Dog> getApiPetMapper() {
		return dogToDogWithoutDogsInBreedsMapper;
	}

	@Override
	protected JpaRepository<Dog, Integer> getPetRepository() {
		return dogRepository;
	}

	@Override
	protected JpaRepository<DogBreed, Integer> getBreedRepository() {
		return dogBreedRepository;
	}

	@Override
	public String uploadFile(MultipartFile multipartFile) throws UploadS3FileException {
		return s3Service.uploadMultipartFileToS3(multipartFile, "dogs");
	}

	@Override
	public PetToPetMapper<Dog, Dog> getPetWithoutBreedsMapper() {
		return dogToDogWithoutBreedsMapper;
	}
}
