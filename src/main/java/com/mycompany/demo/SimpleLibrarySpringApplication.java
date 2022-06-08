package com.mycompany.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimpleLibrarySpringApplication {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/SimpleLibrarySpring");
		SpringApplication.run(SimpleLibrarySpringApplication.class, args);
		System.out.println("ApplicationStarter has started");
	}

}
