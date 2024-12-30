package tpasma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("tpasma.repository")
public class Tp1AsmaApplication {

	public static void main(String[] args) {
		SpringApplication.run(Tp1AsmaApplication.class, args);
	}

}
