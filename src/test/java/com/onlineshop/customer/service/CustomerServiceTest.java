package com.onlineshop.customer.service;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.onlineshop.customer.dto.CustomerResponse;
import com.onlineshop.customer.dto.PageResponse;

@SpringBootTest
public class CustomerServiceTest {
	@Autowired
	private CustomerService customerService;
	
	@Test
	public void should_return_the_requested_page_of_customers() {
		PageResponse<CustomerResponse> customerPage = customerService.getAll(Optional.empty(), Optional.empty(), 0, 25);
	
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
	public void should_filter_customers_by_country_name_when_country_exists() {
		PageResponse<CustomerResponse> customerPage = customerService.getAll(Optional.of("Cameron"), Optional.empty(), 0, 10);
	
		Assertions.assertTrue(customerPage.getItems().size() > 0);
		customerPage.getItems().stream().forEach(customer -> Assertions.assertEquals("Cameron", customer.getCountryName()));
	}
	
	@Test
	public void should_return_empty_page_when_country_does_not_exist() {
		PageResponse<CustomerResponse> customerPage = customerService.getAll(Optional.of("Egypt"), Optional.empty(), 0, 10);
	
		Assertions.assertEquals(0, customerPage.getItems().size());
		Assertions.assertEquals(0, customerPage.getItemsTotalCount());
	}
	
	@Test
	public void should_filter_customers_by_phoneVaild() {
		PageResponse<CustomerResponse> customerPage = customerService.getAll(Optional.empty(), Optional.of(true), 0, 10);
	
		Assertions.assertTrue(customerPage.getItems().size() > 0);
		customerPage.getItems().stream().forEach(customer -> Assertions.assertTrue(customer.isPhoneValid()));
	}
	
	@Test
	public void should_filter_customers_by_phoneInvaild() {
		PageResponse<CustomerResponse> customerPage = customerService.getAll(Optional.empty(), Optional.of(false), 0, 10);
		
		Assertions.assertTrue(customerPage.getItems().size() > 0);
		customerPage.getItems().stream().forEach(customer -> Assertions.assertFalse(customer.isPhoneValid()));
	}
}
