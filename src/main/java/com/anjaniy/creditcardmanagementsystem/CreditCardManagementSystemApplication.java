package com.anjaniy.creditcardmanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class CreditCardManagementSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(CreditCardManagementSystemApplication.class, args);
	}
}
