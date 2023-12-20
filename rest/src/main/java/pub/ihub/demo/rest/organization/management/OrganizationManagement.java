package pub.ihub.demo.rest.organization.management;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pub.ihub.demo.rest.OrganizationAddEvent;
import pub.ihub.demo.rest.OrganizationRemoveEvent;
import pub.ihub.demo.rest.department.DepartmentDTO;
import pub.ihub.demo.rest.department.DepartmentInternalAPI;
import pub.ihub.demo.rest.employee.EmployeeDTO;
import pub.ihub.demo.rest.employee.EmployeeInternalAPI;
import pub.ihub.demo.rest.organization.OrganizationDTO;
import pub.ihub.demo.rest.organization.OrganizationExternalAPI;
import pub.ihub.demo.rest.organization.mapper.OrganizationMapper;
import pub.ihub.demo.rest.organization.repository.OrganizationRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrganizationManagement implements OrganizationExternalAPI {

	private final ApplicationEventPublisher events;
	private final OrganizationRepository repository;
	private final DepartmentInternalAPI departmentInternalAPI;
	private final EmployeeInternalAPI employeeInternalAPI;
	private final OrganizationMapper mapper;

	@Override
	public OrganizationDTO findByIdWithEmployees(Long id) {
		OrganizationDTO dto = repository.findDTOById(id);
		List<EmployeeDTO> dtos = employeeInternalAPI.getEmployeesByOrganizationId(id);
		dto.employees().addAll(dtos);
		return dto;
	}

	@Override
	public OrganizationDTO findByIdWithDepartments(Long id) {
		OrganizationDTO dto = repository.findDTOById(id);
		List<DepartmentDTO> dtos = departmentInternalAPI.getDepartmentsByOrganizationId(id);
		dto.departments().addAll(dtos);
		return dto;
	}

	@Override
	public OrganizationDTO findByIdWithDepartmentsAndEmployees(Long id) {
		OrganizationDTO dto = repository.findDTOById(id);
		List<DepartmentDTO> dtos = departmentInternalAPI.getDepartmentsByOrganizationIdWithEmployees(id);
		dto.departments().addAll(dtos);
		return dto;
	}

	@Override
	@Transactional
	public OrganizationDTO add(OrganizationDTO organization) {
		OrganizationDTO dto = mapper.organizationToOrganizationDTO(
			repository.save(mapper.organizationDTOToOrganization(organization))
		);
		events.publishEvent(new OrganizationAddEvent(dto.id()));
		return dto;
	}

	@Override
	@Transactional
	public void remove(Long id) {
		repository.deleteById(id);
		events.publishEvent(new OrganizationRemoveEvent(id));
	}

}
