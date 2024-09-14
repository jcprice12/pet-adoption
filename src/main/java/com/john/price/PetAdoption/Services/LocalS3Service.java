package com.john.price.PetAdoption.Services;

import java.io.File;
import java.net.InetAddress;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.john.price.PetAdoption.Exceptions.UploadS3FileException;

@Component
@Profile("dev")
public class LocalS3Service implements IS3Service {

    @Autowired
    Environment environment;

    @Override
    public String uploadMultipartFileToS3(MultipartFile multipartFile, String keyPrefix) throws UploadS3FileException {
        try {
            String originalFileName = multipartFile.getOriginalFilename();
            String filePath = String.format("local-images/%s/%d_%s", keyPrefix, System.currentTimeMillis(),
                    originalFileName);
            Path absolutePath = Paths.get(System.getProperty("user.dir"), "src/main/resources/static", filePath);
            File file = absolutePath.toFile();
            file.getParentFile().mkdirs();
            multipartFile.transferTo(file);
            return String.format("%s:%s/%s", InetAddress.getLoopbackAddress().getHostName(),
                    environment.getProperty("local.server.port"), filePath);
        } catch (Exception e) {
            throw new UploadS3FileException(e);
        }
    }
}
