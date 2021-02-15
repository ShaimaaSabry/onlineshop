package com.onlineshop.customer;

import java.util.Collections;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
public class CustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

	@Bean
	public Docket Swagger() {
		ApiInfo apiInfo = new ApiInfo("Online Shop: Customers API",
				"A RESTful API service for retrieving and filtering customers.", "1.0", null, null, null,
				null, Collections.emptyList());

		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.onlineshop.customer")).build().apiInfo(apiInfo);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
