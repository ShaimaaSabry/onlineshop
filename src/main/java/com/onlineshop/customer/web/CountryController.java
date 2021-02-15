package com.onlineshop.customer.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlineshop.customer.dto.CountryResponse;
import com.onlineshop.customer.service.CountryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("countries")
@Api(tags = "Countries")
public class CountryController {
	private final CountryService countryService;
	
	public CountryController(CountryService countryService) {
		this.countryService = countryService;
	}
	
	@GetMapping
	@ApiOperation(value = "Get Countries", notes = "Get a list of available countries.")
	public List<CountryResponse> getAll() {
		return countryService.getAll();
	}
}
