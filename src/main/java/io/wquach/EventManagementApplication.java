package io.wquach;

import com.fasterxml.jackson.core.JsonFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EventManagementApplication {
    @Bean
    public JsonFactory jsonFactory() {
        return new JsonFactory();
    }

	public static void main(String[] args) {
		SpringApplication.run(EventManagementApplication.class, args);
	}
}
