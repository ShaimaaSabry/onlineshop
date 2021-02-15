package com.onlineshop.customer.service;

import java.util.Optional;

import com.onlineshop.customer.dto.CustomerResponse;
import com.onlineshop.customer.dto.PageResponse;

public interface CustomerService {
	PageResponse<CustomerResponse> getAll(Optional<String> countryName, Optional<Boolean> phoneValid, int pageIndex, int pageSize);
}
