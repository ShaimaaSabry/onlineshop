package com.onlineshop.customer.service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.onlineshop.customer.dto.CountryResponse;

@SpringBootTest
public class CountryServiceTest {
	@Autowired
	private CountryService countryService;
	
	@Test
	public void should_return_list_of_countries() {
		List<CountryResponse> countryList = countryService.getAll();
		
		Assertions.assertTrue(countryList.size() > 0);
		Assertions.assertNotNull(countryList.get(0).getName());
	}
}
