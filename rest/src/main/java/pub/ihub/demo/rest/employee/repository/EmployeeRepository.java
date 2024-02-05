package pub.ihub.demo.rest.employee.repository;

import org.springframework.data.repository.CrudRepository;
import pub.ihub.demo.rest.employee.EmployeeDTO;
import pub.ihub.demo.rest.employee.model.Employee;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	List<EmployeeDTO> findByDepartmentId(Long departmentId);

	List<EmployeeDTO> findByOrganizationId(Long organizationId);

	void deleteByOrganizationId(Long organizationId);

}
