package com.onlineshop.customer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.onlineshop.customer.domain.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
	Page<Customer> findAll(Pageable pageable);
	Page<Customer> findByCountryNameIgnoreCase(String countryName, Pageable pageable);
	Page<Customer> findByPhoneValid(Boolean phoneValid, Pageable pageable);
	Page<Customer> findByCountryNameIgnoreCaseAndPhoneValid(String countryName, Boolean phoneValid, Pageable pageable);
	
	Page<Customer> findByPhoneStartsWith(String code, Pageable pageable);
	//Page<Customer> findByPhoneValid(Boolean phoneValid, Pageable pageable);
	Page<Customer> findByPhoneStartsWithAndPhoneValid(String code, Boolean phoneValid, Pageable pageable);
	
	@Query(nativeQuery = true, value = "SELECT * FROM customer WHERE phone REGEXP '(212) [5-9]%'")
	Page<Customer> findByPhoneStartsWithAndPhoneLike(String code, String phoneRegex, Pageable pageable);
}
