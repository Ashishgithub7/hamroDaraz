package com.ecommerce.hamroDaraz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class HamroDarazApplication {

	public static void main(String[] args) {
		SpringApplication.run(HamroDarazApplication.class, args);
	}

}
