package pub.ihub.demo.rest.department;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.Scenario;
import pub.ihub.demo.rest.OrganizationAddEvent;
import pub.ihub.demo.rest.OrganizationRemoveEvent;
import pub.ihub.demo.rest.department.repository.DepartmentRepository;

@ApplicationModuleTest(ApplicationModuleTest.BootstrapMode.DIRECT_DEPENDENCIES)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DepartmentModuleTests {

	private static final long TEST_ID = 100;

	@Autowired
	DepartmentRepository repository;

	@Test
	@Order(1)
	void shouldAddDepartmentsOnEvent(Scenario scenario) {
		scenario.publish(new OrganizationAddEvent(TEST_ID))
			.andWaitForStateChange(() -> repository.findByOrganizationId(TEST_ID))
			.andVerify(result -> {
				assert !result.isEmpty();
			});
	}

	@Test
	@Order(2)
	void shouldRemoveDepartmentsOnEvent(Scenario scenario) {
		scenario.publish(new OrganizationRemoveEvent(TEST_ID))
			.andWaitForStateChange(() -> repository.findByOrganizationId(TEST_ID))
			.andVerify(result -> {
				assert result.isEmpty();
			});
	}
}
