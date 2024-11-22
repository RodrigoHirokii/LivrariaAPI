package io.github.rodrigohirokii.jogosapi2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Jogosapi2Application {

	public static void main(String[] args) {
		SpringApplication.run(Jogosapi2Application.class, args);
	}

}
