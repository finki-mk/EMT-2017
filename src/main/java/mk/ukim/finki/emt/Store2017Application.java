package mk.ukim.finki.emt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;

@SpringBootApplication
public class Store2017Application extends WebMvcAutoConfiguration {

	public static void main(String[] args) {
		SpringApplication.run(Store2017Application.class, args);
	}
}
