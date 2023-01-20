package com.semicolon.africa.House.Management.System;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.semicolon.africa.House.Management.System")
public class HouseManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HouseManagementSystemApplication.class, args);
	}

//	@Bean
//	PasswordEncoder passwordEncoder(){
//		return new BCryptPasswordEncoder();
//	}

}
