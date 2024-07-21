package com.vinsguru;

import org.springframework.boot.SpringApplication;

public class TestCustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(CustomerServiceApplication::main).with(TestContainersConfiguration.class).run(args);
	}

}
