package com.example.Argo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableJpaRepositories(basePackageClasses = UserRepo.class)
public class ArgoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArgoApplication.class, args);
	}

}
