package com.anjaniy.creditcardmanagementsystem.integration;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

public abstract class CreditCardManagementSystemBaseIT {

    private static final String DOCKER_IMAGE = "postgres:latest";

    private static final String DATABASE_NAME = "credit_card_management_system_test_db";

    private static final String USERNAME = "postgres";

    private static final String PASSWORD = "password";

    static final PostgreSQLContainer<?> container;

    static {
        container = new PostgreSQLContainer<>(DOCKER_IMAGE)
                .withDatabaseName(DATABASE_NAME)
                .withUsername(USERNAME)
                .withPassword(PASSWORD);
    }

    @DynamicPropertySource
    static void configureTestProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }

}
