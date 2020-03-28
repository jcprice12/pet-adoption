package com.john.price.PetAdoption.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.john.price.PetAdoption.Exceptions.UploadS3FileException;
import com.john.price.PetAdoption.Functions.CatToCatWithoutBreedsMapper;
import com.john.price.PetAdoption.Functions.CatToCatWithoutCatsInBreedsMapper;
import com.john.price.PetAdoption.Functions.PetToPetMapper;
import com.john.price.PetAdoption.Models.Cat;
import com.john.price.PetAdoption.Models.CatBreed;
import com.john.price.PetAdoption.Repositories.CatBreedRepository;
import com.john.price.PetAdoption.Repositories.CatRepository;

@Component
public class CatService extends PetWithBreedsService<Cat, CatBreed> {

	@Autowired
	private CatToCatWithoutCatsInBreedsMapper catToCatWithoutCatsInBreedsMapper;
	
	@Autowired
	private CatToCatWithoutBreedsMapper catToCatWithoutBreedsMapper;

	@Autowired
	private CatRepository catRepository;

	@Autowired
	private CatBreedRepository catBreedRepository;

	@Autowired
	private S3Service s3Service;

	@Override
	protected PetToPetMapper<Cat, Cat> getApiPetMapper() {
		return catToCatWithoutCatsInBreedsMapper;
	}

	@Override
	protected JpaRepository<Cat, Integer> getPetRepository() {
		return catRepository;
	}

	@Override
	protected JpaRepository<CatBreed, Integer> getBreedRepository() {
		return catBreedRepository;
	}

	@Override
	public String uploadFile(MultipartFile multipartFile) throws UploadS3FileException {
		return s3Service.uploadMultipartFileToS3(multipartFile, "cats");
	}

	@Override
	public PetToPetMapper<Cat, Cat> getPetWithoutBreedsMapper() {
		return catToCatWithoutBreedsMapper;
	}
}
