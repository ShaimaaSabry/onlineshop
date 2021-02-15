package com.onlineshop.customer.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="customer")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Customer {
	@Id
	private Long id;
	
	private String name;
	
	private String phone;
	
	private Boolean phoneValid;
	
	@ManyToOne
	@JoinColumn(name = "country_id")
	private Country country;
}
