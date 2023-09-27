package br.com.rei.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
	
	@Bean
	public OpenAPI CustomOpenApi() {
		return new OpenAPI()
				.info(new Info()
						.title("RESTful api with java and springboot training")
						.version("v1")
						.description("Lorem ipsum dolor sit ammet")
						.termsOfService("https://longliverei.github.io/")
						.license(
								new License()
								.name("Apache 2.0")
								.url("https://longliverei.github.io/")
								)
						);
	}
}
