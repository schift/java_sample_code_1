package pl.riscosoftware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.riscosoftware.service.InitWithDataService;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class RFriendsApplication {

	public static void main(String[] args) {
		System.setProperty("spring.jackson.serialization.INDENT_OUTPUT", "true");
		ConfigurableApplicationContext context = SpringApplication.run(RFriendsApplication.class, args);
		try {
			context.getBean(InitWithDataService.class).doFill();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
