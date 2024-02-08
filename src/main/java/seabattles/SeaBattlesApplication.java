package seabattles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"seabattles/config", "seabattles/service", "seabattles/controller", "seabattles/aspect", "seabattles/exceptions"})
public class SeaBattlesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeaBattlesApplication.class, args);
	}
}
