package com.onlineshop.customer.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.onlineshop.customer.domain.Country;
import com.onlineshop.customer.dto.CountryResponse;
import com.onlineshop.customer.repository.CountryRepository;

@Service
public class CountryServiceImp implements CountryService {
	private final CountryRepository countryRepository;
	private final ModelMapper modelMapper;

	public CountryServiceImp(CountryRepository countryRepository, ModelMapper modelMapper) {
		this.countryRepository = countryRepository;
		this.modelMapper = modelMapper;
	}
	
	@Override
	public List<CountryResponse> getAll() {
		List<Country> countryList = (List<Country>) countryRepository.findAll();
		
		return countryList.stream().map(country -> modelMapper.map(country, CountryResponse.class)).collect(Collectors.toList());
	}

}
