package com.onlineshop.customer.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onlineshop.customer.dto.CustomerResponse;
import com.onlineshop.customer.dto.PageResponse;
import com.onlineshop.customer.service.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("customers")
@Api(tags = "Customers")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@GetMapping
	@ApiOperation(value = "Get Customers", notes = "Get a paginated list of available customers. The customers can be filtered by country and whether the phone number is valid or not.")
	public PageResponse<CustomerResponse> getAll(
			@RequestParam(required = false) Optional<String> country, 
			@RequestParam(required = false) Optional<Boolean> phoneValid, 
			@RequestParam(defaultValue = "0") Integer pageIndex, 
			@RequestParam(defaultValue = "25") Integer pageSize) {
		return customerService.getAll(country, phoneValid, pageIndex, pageSize);
	}
}
