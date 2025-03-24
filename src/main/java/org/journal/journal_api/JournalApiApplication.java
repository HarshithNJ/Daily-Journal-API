package org.journal.journal_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Journal API", version = "1.0", description = "Journal API Information", 
					contact = @Contact(name = "Harshith N J", email = "Harshith.dev2024@outlook.com")))
public class JournalApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(JournalApiApplication.class, args);
	}

}
