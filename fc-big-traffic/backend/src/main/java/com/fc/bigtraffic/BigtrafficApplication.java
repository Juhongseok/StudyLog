package com.fc.bigtraffic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BigtrafficApplication {

	public static void main(String[] args) {
		SpringApplication.run(BigtrafficApplication.class, args);
	}

}
