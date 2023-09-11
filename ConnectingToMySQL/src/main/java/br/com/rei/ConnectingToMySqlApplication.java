package br.com.rei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class ConnectingToMySqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConnectingToMySqlApplication.class, args);
	}

}
