package com.oldie.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import io.github.cdimascio.dotenv.Dotenv;

@Configuration
@SpringBootApplication
public class BackendApplication {

	static {
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
		dotenv.entries().forEach(entry -> {
			System.setProperty(entry.getKey(), entry.getValue());
		});
		System.out.println("--- DEBUG ENV START ---");
		System.out.println("APP_USER: [" + System.getProperty("POSTGRES_USERNAME") + "]");
		System.out.println("APP_PASS: [" + System.getProperty("POSTGRES_PASSWORD") + "]");
		System.out.println("--- DEBUG ENV END ---");
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
