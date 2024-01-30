package com.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableScheduling  //스케쥴링 활성화 어노테이션(주기적인 작업을 수행할수 있도록 한다)
public class DbdbApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbdbApplication.class, args);
	}
}
