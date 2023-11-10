package kr.ar.mjc.HomeP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HomePApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomePApplication.class, args);
	}

}
