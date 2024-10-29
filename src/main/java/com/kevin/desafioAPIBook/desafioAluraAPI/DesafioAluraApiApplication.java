package com.kevin.desafioAPIBook.desafioAluraAPI;

import com.kevin.desafioAPIBook.desafioAluraAPI.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioAluraApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DesafioAluraApiApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.mostrarMenu();
	}
}
