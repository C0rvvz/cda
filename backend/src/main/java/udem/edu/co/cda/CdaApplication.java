package udem.edu.co.cda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "udem.edu.co.cda.repository")
@EntityScan(basePackages = "udem.edu.co.cda.entities")
public class CdaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CdaApplication.class, args);
	}

}
