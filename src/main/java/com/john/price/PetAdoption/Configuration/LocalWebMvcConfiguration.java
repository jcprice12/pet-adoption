package com.john.price.PetAdoption.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.nio.file.Paths;

@Configuration
@Profile("dev")
public class LocalWebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String uploadPath = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/local-images")
                .toAbsolutePath().toString();
        registry.addResourceHandler("/local-images/**").addResourceLocations("file:" + uploadPath + "/");
    }
}