package com.john.price.PetAdoption;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EndpointsTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void catsEndpointShouldReturnJson() throws Exception{
		this.mockMvc.perform(get("/cats"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));		
	}
	
	@Test
	public void catsIdEndpointShouldReturnJson() throws Exception{
		this.mockMvc.perform(get("/cats/1"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));		
	}
	
	@Test
	public void dogsEndpointShouldReturnJson() throws Exception{
		this.mockMvc.perform(get("/dogs"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));	
	}
	
	@Test
	public void dogsIdEndpointShouldReturnJson() throws Exception{
		this.mockMvc.perform(get("/dogs/1"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));		
	}
}
