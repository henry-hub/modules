package pub.ihub.demo.rest.employee.management;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pub.ihub.demo.rest.OrganizationRemoveEvent;
import pub.ihub.demo.rest.employee.EmployeeDTO;
import pub.ihub.demo.rest.employee.EmployeeExternalAPI;
import pub.ihub.demo.rest.employee.EmployeeInternalAPI;
import pub.ihub.demo.rest.employee.mapper.EmployeeMapper;
import pub.ihub.demo.rest.employee.model.Employee;
import pub.ihub.demo.rest.employee.repository.EmployeeRepository;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class EmployeeManagement implements EmployeeInternalAPI, EmployeeExternalAPI {

	private final EmployeeRepository repository;
	private final EmployeeMapper mapper;

	@Override
	public List<EmployeeDTO> getEmployeesByDepartmentId(Long departmentId) {
		return repository.findByDepartmentId(departmentId);
	}

	@Override
	public List<EmployeeDTO> getEmployeesByOrganizationId(Long id) {
		return repository.findByOrganizationId(id);
	}

	@Override
	@Transactional
	public EmployeeDTO add(EmployeeDTO employee) {
		Employee emp = mapper.employeeDTOToEmployee(employee);
		return mapper.employeeToEmployeeDTO(repository.save(emp));
	}

	@ApplicationModuleListener
	void onRemovedOrganizationEvent(OrganizationRemoveEvent event) {
		log.info("onRemovedOrganizationEvent(orgId={})", event.getId());
		repository.deleteByOrganizationId(event.getId());
	}

}
