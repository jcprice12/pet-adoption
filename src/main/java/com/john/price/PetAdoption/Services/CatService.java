package com.john.price.PetAdoption.Services;

import com.john.price.PetAdoption.Exceptions.UploadS3FileException;
import com.john.price.PetAdoption.Functions.CatToCatWithoutCatsInBreedsMapper;
import com.john.price.PetAdoption.Functions.PetToPetMapper;
import com.john.price.PetAdoption.Models.Cat;
import com.john.price.PetAdoption.Repositories.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class CatService extends PetWithBreedsService<Cat> {

  @Autowired private CatToCatWithoutCatsInBreedsMapper catToCatWithoutCatsInBreedsMapper;

  @Autowired private CatRepository catRepository;

  @Autowired private S3Service s3Service;

  @Override
  protected PetToPetMapper<Cat, Cat> getPetToPetMapper() {
    return catToCatWithoutCatsInBreedsMapper;
  }

  @Override
  protected JpaRepository<Cat, Integer> getRepository() {
    return catRepository;
  }

  @Override
  protected String getIntersectionTableName() {
    return "catbreed_cat";
  }

  @Override
  protected String getPetIdColumnName() {
    return "cat_id";
  }

  @Override
  public String uploadFile(MultipartFile multipartFile) throws UploadS3FileException {
    return s3Service.uploadMultipartFileToS3(multipartFile, "cats");
  }
}
