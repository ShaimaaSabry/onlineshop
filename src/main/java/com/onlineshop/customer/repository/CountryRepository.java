package com.onlineshop.customer.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.onlineshop.customer.domain.Country;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long>{
	Optional<Country> findFirstByCode(String code);
}
