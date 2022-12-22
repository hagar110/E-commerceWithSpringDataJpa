package com.example.EcommerceWithSpringDataJpa;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = { "com/example/EcommerceWithSpringDataJpa.*" })
@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories
public class ECommerceWithSpringDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceWithSpringDataJpaApplication.class, args);
	}

}
