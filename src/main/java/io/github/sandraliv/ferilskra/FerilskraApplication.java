package io.github.sandraliv.ferilskra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class FerilskraApplication {
	public static void main(String[] args) {
		SpringApplication.run(FerilskraApplication.class, args);
	}

}
