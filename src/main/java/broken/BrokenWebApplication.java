package broken;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
/**
 *
 * @author ahm3dhany
 */
@EnableWebSecurity
@SpringBootApplication
public class BrokenWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrokenWebApplication.class, args);
	}
}
