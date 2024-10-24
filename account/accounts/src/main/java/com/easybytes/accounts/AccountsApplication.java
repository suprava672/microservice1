package com.easybytes.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Account microservice RestApi Documentation",
				description="EasyBytes Accounts microservice Documentation",
				version = "v1",
				contact = @Contact(
						name = "suprava",
						email  ="suprava09@gmail.com",
						url = "http://www.easybytes.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "http://www.easybytes.com"
				)

				),
				externalDocs = @ExternalDocumentation(
						description = "EasyBytes Accounts RestApi Documentation",
						url = "http://www.easybytes.com/swagger-ui.html"


				)

)
public class AccountsApplication {
	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
