package es.plexus.hopes.hopesback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HopesBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(HopesBackApplication.class, args);
	}

}
