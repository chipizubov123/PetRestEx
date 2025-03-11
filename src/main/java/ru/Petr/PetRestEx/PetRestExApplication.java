package ru.Petr.PetRestEx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class PetRestExApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetRestExApplication.class, args);
	}

}
