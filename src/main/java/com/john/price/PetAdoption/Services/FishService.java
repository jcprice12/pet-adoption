package com.john.price.PetAdoption.Services;

import com.john.price.PetAdoption.Exceptions.UploadS3FileException;
import com.john.price.PetAdoption.Models.Fish;
import com.john.price.PetAdoption.Repositories.FishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FishService extends PetWithSpeciesService<Fish> {

  @Autowired private FishRepository fishRepository;

  @Autowired private S3Service s3Service;

  @Override
  protected JpaRepository<Fish, Integer> getRepository() {
    return fishRepository;
  }

  @Override
  public String uploadFile(MultipartFile multipartFile) throws UploadS3FileException {
    return s3Service.uploadMultipartFileToS3(multipartFile, "fish");
  }
}
