package com.projetoopotimum.optimum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class OptimumApplication {

	public static void main(String[] args) {
		SpringApplication.run(OptimumApplication.class, args);
	}

}
