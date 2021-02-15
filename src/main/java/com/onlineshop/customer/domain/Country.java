package com.onlineshop.customer.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "country")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Country {
	@Id
	private Long id;
	
	private String name;
	
	private String code;
	
	private String phoneRegex;
}
