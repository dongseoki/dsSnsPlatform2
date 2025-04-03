package com.dssns.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.dssns.common"})
public class CommonApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommonApplication.class, args);
	}

}
