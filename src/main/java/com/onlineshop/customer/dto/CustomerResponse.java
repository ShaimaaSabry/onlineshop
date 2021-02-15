package com.onlineshop.customer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponse {
	private long id;
	private String name;
	private String countryName;
	private String countryCode;
	private String phone;
	private boolean phoneValid;
}
