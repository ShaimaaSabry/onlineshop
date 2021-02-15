package com.onlineshop.customer.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.onlineshop.customer.domain.Customer;
import com.onlineshop.customer.dto.CustomerResponse;
import com.onlineshop.customer.dto.PageResponse;
import com.onlineshop.customer.repository.CustomerRepository;

@Service
public class CustomerServiceImp implements CustomerService {
	private final CustomerRepository customerRepository;
	private final ModelMapper modelMapper;

	public CustomerServiceImp(CustomerRepository customerRepository, ModelMapper modelMapper) {
		this.customerRepository = customerRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public PageResponse<CustomerResponse> getAll(Optional<String> countryName, Optional<Boolean> phoneValid,
			int pageIndex, int pageSize) {
		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		Page<Customer> customerPage;
		if (countryName.isPresent() && phoneValid.isPresent()) {
			customerPage = customerRepository.findByCountryNameIgnoreCaseAndPhoneValid(countryName.get(),
					phoneValid.get(), pageable);

		} else if (countryName.isPresent()) {
			customerPage = customerRepository.findByCountryNameIgnoreCase(countryName.get(), pageable);
		} else if (phoneValid.isPresent()) {
			customerPage = customerRepository.findByPhoneValid(phoneValid.get(), pageable);
		} else {
			customerPage = customerRepository.findAll(pageable);
		}

		List<CustomerResponse> customerResponseList = customerPage.toList().stream()
				.map(customer -> modelMapper.map(customer, CustomerResponse.class)).collect(Collectors.toList());
		PageResponse<CustomerResponse> pageResponse = new PageResponse<>(customerPage.getNumber(),
				customerPage.getSize(), (int) customerPage.getTotalElements(), customerResponseList);
		return pageResponse;
	}
}
