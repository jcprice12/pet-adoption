package com.john.price.PetAdoption;

import static org.mockito.Mockito.times;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.SpringApplication;

@RunWith(PowerMockRunner.class)
@PrepareForTest({App.class, SpringApplication.class})
public class AppTest{
	
	@SuppressWarnings("deprecation")
	@Test
    public void checkApp()
    {	
		String[] args = new String[0];	
        PowerMockito.mockStatic(SpringApplication.class);
        App.main(args);
        PowerMockito.verifyStatic(times(1));
        SpringApplication.run(App.class, args);
    }
}
