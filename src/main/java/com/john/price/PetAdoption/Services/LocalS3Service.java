package com.john.price.PetAdoption.Services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.john.price.PetAdoption.Exceptions.UploadS3FileException;

@Component
@Profile("dev")
public class LocalS3Service implements IS3Service {

  @Override
  public String uploadMultipartFileToS3(MultipartFile multipartFile, String keyPrefix) throws UploadS3FileException {
    String fname = multipartFile.getOriginalFilename();
    return fname;
  }
}
