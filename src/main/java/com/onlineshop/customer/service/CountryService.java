package com.onlineshop.customer.service;

import java.util.List;

import com.onlineshop.customer.dto.CountryResponse;

public interface CountryService {
	List<CountryResponse> getAll();
}
