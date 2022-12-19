package com.bennyTech.business.productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class BusinessServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusinessServiceApplication.class, args);
	}

}
