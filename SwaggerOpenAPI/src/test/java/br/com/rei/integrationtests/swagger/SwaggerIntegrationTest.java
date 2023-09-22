package br.com.rei.integrationtests.swagger;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.rei.configs.TestConfigs;
import br.com.rei.integrationtests.testcontainers.AbstractIntegrationTests;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SwaggerIntegrationTest extends AbstractIntegrationTests {

	@Test
	public void shouldDisplaySwaggerUiPage() {
		var content =
			given()
				.basePath("/swagger-ui/index.html")
				.port(TestConfigs.SERVER_PORT)
				.when()
					.get()
				.then()
					.statusCode(200)
				.extract()
					.body()
						.asString();
		
		Assertions.assertTrue(content.contains("Swagger UI"));
	}

}
