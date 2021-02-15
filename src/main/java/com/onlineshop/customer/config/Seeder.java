package com.onlineshop.customer.config;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.onlineshop.customer.domain.Country;
import com.onlineshop.customer.domain.Customer;
import com.onlineshop.customer.repository.CountryRepository;
import com.onlineshop.customer.repository.CustomerRepository;

@Component
public class Seeder implements CommandLineRunner {
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CountryRepository countryRepository;

	@Override
	public void run(String... args) throws Exception {
		int pageIndex = 0, pageSize = 100;
		while (true) {
			Pageable pageable = PageRequest.of(pageIndex, pageSize);
			Page<Customer> customerPage = customerRepository.findAll(pageable);
			customerPage.toList().stream().forEach(customer -> {
				setCountry(customer);
				customerRepository.save(customer);
			});

			if (customerPage.isLast()) {
				break;
			}
			pageIndex++;
		}
	}
	
	private Customer setCountry(Customer customer) {
		Pattern codePattern = Pattern.compile("\\(.{3}\\)");
		Matcher codeMatcher = codePattern.matcher(customer.getPhone());
		if (!codeMatcher.find()) {
			return customer;
		}
		String countryCode = codeMatcher.group(0).substring(1, codeMatcher.group(0).length() - 1);

		Optional<Country> country = countryRepository.findFirstByCode(countryCode);
		if (country.isEmpty()) {
			return customer;
		}
		customer.setCountry(country.get());

		boolean valid = false;
		Pattern phonePattern = Pattern.compile(country.get().getPhoneRegex());
		Matcher phoneMatcher = phonePattern.matcher(customer.getPhone());
		if (phoneMatcher.matches()) {
			valid = true;
		}
		customer.setPhoneValid(valid);

		return customer;
	}
}
