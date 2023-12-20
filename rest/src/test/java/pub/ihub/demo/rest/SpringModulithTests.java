package pub.ihub.demo.rest;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

public class SpringModulithTests {

	ApplicationModules modules = ApplicationModules.of(Application.class);

	@Test
	void shouldBeCompliant() {
		modules.verify();
	}

	@Test
	void writeDocumentationSnippets() {
		new Documenter(modules)
			.writeModuleCanvases()
			.writeModulesAsPlantUml()
			.writeIndividualModulesAsPlantUml();
	}
}
