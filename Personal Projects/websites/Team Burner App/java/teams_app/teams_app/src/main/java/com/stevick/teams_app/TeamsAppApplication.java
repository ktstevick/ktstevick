package com.stevick.teams_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TeamsAppApplication {
	public static void main(String[] args) {
		System.out.println("Hello, Pokemon World!");
		SpringApplication.run(TeamsAppApplication.class, args);
	}
}
