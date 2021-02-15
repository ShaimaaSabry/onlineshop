package com.onlineshop.customer.web;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlineshop.customer.dto.CountryResponse;

@SpringBootTest
@AutoConfigureMockMvc
public class CountryControllerTest {
	@Autowired 
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void should_return_200_and_list_of_countries() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/countries");
		MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
		
		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
		List<CountryResponse> countryList = objectMapper.readValue(response.getContentAsString(),
				new TypeReference<List<CountryResponse>>() {
				});
		Assertions.assertTrue(countryList.size() > 0);
		Assertions.assertNotNull(countryList.get(0).getName());
	}
}
