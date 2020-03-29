package com.john.price.PetAdoption;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/** Pet Adoption */

/*
 * @SpringBootApplication is a combination of:
 * - @Configuration: Tags the class as a source of bean definitions for the application context.
 * - @EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings.
 * - @EnableWebMvc: This flags the application as a web application and activates key behaviors such as setting up a DispatcherServlet
 * - @ComponentScan: Tells Spring to look for other components, configurations, and services in the TestProject package, allowing it to find the controllers.
 */
@SpringBootApplication()
@EnableCaching
public class App {
  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }
}
