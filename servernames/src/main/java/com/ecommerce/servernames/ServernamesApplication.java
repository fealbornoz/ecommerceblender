package com.ecommerce.servernames;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEurekaServer	
public class ServernamesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServernamesApplication.class, args);
	}

}
