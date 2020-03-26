package com.john.price.PetAdoption.Services;

import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClientBuilder;
import com.amazonaws.services.securitytoken.model.AssumeRoleRequest;
import com.amazonaws.services.securitytoken.model.Credentials;
import com.john.price.PetAdoption.Exceptions.UploadS3FileException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class S3Service {

  private static final String BUCKET_NAME = System.getenv("PETS_S3_BUCKET_NAME");
  private static final String ROLE_ARN = System.getenv("PUT_IMAGES_ROLE");
  private static final Integer SESSION_DURATION_IN_SECONDS = 900;

  public String uploadMultipartFileToS3(MultipartFile multipartFile, String keyPrefix)
      throws UploadS3FileException {
    try {
      AmazonS3 s3Client = createS3Client(createTemporaryCredentials());

      ObjectMetadata objectMetadata = new ObjectMetadata();
      objectMetadata.setContentType(multipartFile.getContentType());
      objectMetadata.setContentMD5(createBase64MD5Digest(multipartFile.getBytes()));
      objectMetadata.setContentLength(multipartFile.getSize());

      String key =
          String.format(
              "pets/%s/%d_%s",
              keyPrefix, System.currentTimeMillis(), multipartFile.getOriginalFilename());
      s3Client.putObject(BUCKET_NAME, key, multipartFile.getInputStream(), objectMetadata);
      return s3Client.getUrl(BUCKET_NAME, key).toString();
    } catch (SdkClientException | IOException | NoSuchAlgorithmException e) {
      throw new UploadS3FileException(e);
    }
  }

  private Credentials createTemporaryCredentials() {
    String roleSessionName =
        String.format(
            "%d_%s",
            System.currentTimeMillis(),
            SecurityContextHolder.getContext().getAuthentication().getName());
    AWSSecurityTokenService stsClient =
        AWSSecurityTokenServiceClientBuilder.standard()
            .withCredentials(new EnvironmentVariableCredentialsProvider())
            .withRegion(Regions.US_EAST_2)
            .build();
    AssumeRoleRequest roleRequest =
        new AssumeRoleRequest()
            .withRoleArn(ROLE_ARN)
            .withRoleSessionName(roleSessionName)
            .withDurationSeconds(SESSION_DURATION_IN_SECONDS);
    return stsClient.assumeRole(roleRequest).getCredentials();
  }

  private AmazonS3 createS3Client(Credentials creds) {
    BasicSessionCredentials awsCredentials =
        new BasicSessionCredentials(
            creds.getAccessKeyId(), creds.getSecretAccessKey(), creds.getSessionToken());
    return AmazonS3ClientBuilder.standard()
        .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
        .withRegion(Regions.US_EAST_2)
        .build();
  }

  private String createBase64MD5Digest(byte[] bytes) throws NoSuchAlgorithmException {
    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
    messageDigest.update(bytes);
    byte[] digest = messageDigest.digest();
    return Base64.getEncoder().encodeToString(digest);
  }
}
