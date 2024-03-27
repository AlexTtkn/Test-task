package com.example.testtask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement //почитать
public class TesttaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(TesttaskApplication.class, args);
	}

}
