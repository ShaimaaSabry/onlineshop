package com.onlineshop.customer.web;

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

import com.onlineshop.customer.dto.CustomerResponse;
import com.onlineshop.customer.dto.PageResponse;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void should_return_200_and_the_first_page_of_customers_when_page_index_and_page_size_are_not_specified() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/customers");
		MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
		PageResponse<CustomerResponse> customerPage = objectMapper.readValue(response.getContentAsString(),
				new TypeReference<PageResponse<CustomerResponse>>() {
				});
		Assertions.assertEquals(0, customerPage.getPageIndex());
		Assertions.assertEquals(25, customerPage.getPageSize());
		Assertions.assertTrue(customerPage.getItems().size() <= 25);
		Assertions.assertTrue(customerPage.getItems().size() > 0);
		Assertions.assertNotNull(customerPage.getItems().get(0).getId());
		Assertions.assertNotNull(customerPage.getItems().get(0).getName());
		Assertions.assertNotNull(customerPage.getItems().get(0).getCountryName());
		Assertions.assertNotNull(customerPage.getItems().get(0).getCountryCode());
		Assertions.assertNotNull(customerPage.getItems().get(0).getPhone());
		Assertions.assertNotNull(customerPage.getItems().get(0).isPhoneValid());
	}
	
	@Test
	public void should_return_200_and_the_requested_page_of_customers_when_page_index_and_page_size_are_specified() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/customers").param("pageIndex", "1").param("pageSize", "10");
		MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
		PageResponse<CustomerResponse> customerPage = objectMapper.readValue(response.getContentAsString(),
				new TypeReference<PageResponse<CustomerResponse>>() {
		});
		Assertions.assertEquals(1, customerPage.getPageIndex());
		Assertions.assertEquals(10, customerPage.getPageSize());
		Assertions.assertTrue(customerPage.getItems().size() <= 10);
		Assertions.assertTrue(customerPage.getItems().size() > 0);
		Assertions.assertNotNull(customerPage.getItems().get(0).getId());
		Assertions.assertNotNull(customerPage.getItems().get(0).getName());
		Assertions.assertNotNull(customerPage.getItems().get(0).getCountryName());
		Assertions.assertNotNull(customerPage.getItems().get(0).getCountryCode());
		Assertions.assertNotNull(customerPage.getItems().get(0).getPhone());
		Assertions.assertNotNull(customerPage.getItems().get(0).isPhoneValid());
	}
	
	@Test
	public void should_return_200_and_filter_customers_by_country_name_when_country_exists() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/customers").param("country", "Cameron");
		MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
	
		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
		PageResponse<CustomerResponse> customerPage = objectMapper.readValue(response.getContentAsString(),
				new TypeReference<PageResponse<CustomerResponse>>() {
		});
		Assertions.assertTrue(customerPage.getItems().size() > 0);
		customerPage.getItems().stream().forEach(customer -> Assertions.assertEquals("Cameron", customer.getCountryName()));
	}
	
	@Test
	public void should_return200_and_empty_page_when_country_does_not_exist() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/customers").param("country", "Egypt");
		MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
	
		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
		PageResponse<CustomerResponse> customerPage = objectMapper.readValue(response.getContentAsString(),
				new TypeReference<PageResponse<CustomerResponse>>() {
		});
		Assertions.assertEquals(0, customerPage.getItems().size());
		Assertions.assertEquals(0, customerPage.getItemsTotalCount());
	}
	
	@Test
	public void should_return_200_and_filter_customers_by_phoneVaild() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/customers").param("phoneValid", "true");
		MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
	
		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
		PageResponse<CustomerResponse> customerPage = objectMapper.readValue(response.getContentAsString(),
				new TypeReference<PageResponse<CustomerResponse>>() {
		});
		Assertions.assertTrue(customerPage.getItems().size() > 0);
		customerPage.getItems().stream().forEach(customer -> Assertions.assertTrue(customer.isPhoneValid()));
	}
	
	@Test
	public void should_return_200_and_filter_customers_by_phoneInvaild() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/customers").param("phoneValid", "false");
		MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
	
		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
		PageResponse<CustomerResponse> customerPage = objectMapper.readValue(response.getContentAsString(),
				new TypeReference<PageResponse<CustomerResponse>>() {
		});
		Assertions.assertTrue(customerPage.getItems().size() > 0);
		customerPage.getItems().stream().forEach(customer -> Assertions.assertFalse(customer.isPhoneValid()));
	}
}
