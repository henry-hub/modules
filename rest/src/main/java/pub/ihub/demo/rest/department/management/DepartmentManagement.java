package pub.ihub.demo.rest.department.management;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;
import pub.ihub.demo.rest.OrganizationAddEvent;
import pub.ihub.demo.rest.OrganizationRemoveEvent;
import pub.ihub.demo.rest.department.DepartmentDTO;
import pub.ihub.demo.rest.department.DepartmentExternalAPI;
import pub.ihub.demo.rest.department.DepartmentInternalAPI;
import pub.ihub.demo.rest.department.mapper.DepartmentMapper;
import pub.ihub.demo.rest.department.repository.DepartmentRepository;
import pub.ihub.demo.rest.employee.EmployeeDTO;
import pub.ihub.demo.rest.employee.EmployeeInternalAPI;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class DepartmentManagement implements DepartmentInternalAPI, DepartmentExternalAPI {

	private final DepartmentRepository repository;
	private final EmployeeInternalAPI employeeInternalAPI;
	private final DepartmentMapper mapper;

	@Override
	public DepartmentDTO getDepartmentByIdWithEmployees(Long id) {
		DepartmentDTO d = repository.findDTOById(id);
		List<EmployeeDTO> dtos = employeeInternalAPI.getEmployeesByDepartmentId(id);
		d.employees().addAll(dtos);
		return d;
	}

	@ApplicationModuleListener
	void onNewOrganizationEvent(OrganizationAddEvent event) {
		log.info("onNewOrganizationEvent(orgId={})", event.getId());
		add(new DepartmentDTO(null, event.getId(), "HR"));
		add(new DepartmentDTO(null, event.getId(), "Management"));
	}

	@ApplicationModuleListener
	void onRemovedOrganizationEvent(OrganizationRemoveEvent event) {
		log.info("onRemovedOrganizationEvent(orgId={})", event.getId());
		repository.deleteByOrganizationId(event.getId());
	}

	@Override
	public DepartmentDTO add(DepartmentDTO department) {
		return mapper.departmentToDepartmentDTO(
			repository.save(mapper.departmentDTOToDepartment(department))
		);
	}

	@Override
	public List<DepartmentDTO> getDepartmentsByOrganizationId(Long id) {
		return repository.findByOrganizationId(id);
	}

	@Override
	public List<DepartmentDTO> getDepartmentsByOrganizationIdWithEmployees(Long id) {
		List<DepartmentDTO> departments = repository.findByOrganizationId(id);
		for (DepartmentDTO dep : departments) {
			dep.employees().addAll(employeeInternalAPI.getEmployeesByDepartmentId(dep.id()));
		}
		return departments;
	}
}
