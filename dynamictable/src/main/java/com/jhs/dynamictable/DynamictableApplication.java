package com.jhs.dynamictable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class DynamictableApplication {

	public static void main(String[] args) {
		SpringApplication.run(DynamictableApplication.class, args);
	}

}
