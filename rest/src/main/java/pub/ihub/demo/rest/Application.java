package pub.ihub.demo.rest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author liheng
 */
@SpringBootApplication
@OpenAPIDefinition(
	info = @io.swagger.v3.oas.annotations.info.Info(
		title = "GatewayManagement",
		version = "1.0",
		description = "sssssdsdsd",
		termsOfService = "termsOfService",
		license = @io.swagger.v3.oas.annotations.info.License(
			name = "aaaaaaaaaaaaaaaa",
			url = "https://ihub.pub"
		),
		contact = @io.swagger.v3.oas.annotations.info.Contact(
			name = "fffff",
			url = "https://ihub.pub",
			email = "henry@ihub.pub"
		)
	),
	tags = {
		@io.swagger.v3.oas.annotations.tags.Tag(
			name = "DepartmentExternalAPI",
			description = "description",
			externalDocs = @io.swagger.v3.oas.annotations.ExternalDocumentation(
				description = "description",
				url = "https://ihub.pub",
				extensions = {}
			)
		)
	},
	servers = {
		@io.swagger.v3.oas.annotations.servers.Server(
			url = "https://ihub.pub",
			description = "description",
			extensions = {}
		)
	},
	security = {
		@io.swagger.v3.oas.annotations.security.SecurityRequirement(
			name = "name",
			scopes = {"scopes"}
		)
	},
	externalDocs = @io.swagger.v3.oas.annotations.ExternalDocumentation(
		description = "description",
		url = "https://ihub.pub",
		extensions = {}
	),
	extensions = {}
)
public class Application {

	public static void main(final String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
