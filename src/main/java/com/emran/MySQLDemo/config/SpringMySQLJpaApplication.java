package com.emran.MySQLDemo.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.emran.MySQLDemo")
@EntityScan("com.emran.MySQLDemo.model")
@EnableJpaRepositories(basePackages = {"com.emran.MySQLDemo.repo"})

//add extends SpringBootServletInitializer this for war
public class SpringMySQLJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMySQLJpaApplication.class, args);

	}
}

