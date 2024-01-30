package com.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class OnePlanner2Application {

	public static void main(String[] args) {
		SpringApplication.run(OnePlanner2Application.class, args);
	}

}
