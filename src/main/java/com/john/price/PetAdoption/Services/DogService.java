package com.john.price.PetAdoption.Services;

import com.john.price.PetAdoption.Exceptions.UploadS3FileException;
import com.john.price.PetAdoption.Functions.DogToDogWithoutDogsInBreedsMapper;
import com.john.price.PetAdoption.Functions.PetToPetMapper;
import com.john.price.PetAdoption.Models.Dog;
import com.john.price.PetAdoption.Repositories.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class DogService extends PetWithBreedsService<Dog> {

  @Autowired private DogToDogWithoutDogsInBreedsMapper dogToDogWithoutDogsInBreedsMapper;

  @Autowired private DogRepository dogRepository;

  @Autowired private S3Service s3Service;

  @Override
  protected PetToPetMapper<Dog, Dog> getPetToPetMapper() {
    return dogToDogWithoutDogsInBreedsMapper;
  }

  @Override
  protected JpaRepository<Dog, Integer> getRepository() {
    return dogRepository;
  }

  @Override
  protected String getIntersectionTableName() {
    return "dogbreed_dog";
  }

  @Override
  protected String getPetIdColumnName() {
    return "dog_id";
  }

  @Override
  public String uploadFile(MultipartFile multipartFile) throws UploadS3FileException {
    return s3Service.uploadMultipartFileToS3(multipartFile, "dogs");
  }
}
