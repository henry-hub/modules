package pub.ihub.demo.rest.organization;

import pub.ihub.demo.rest.department.DepartmentDTO;
import pub.ihub.demo.rest.employee.EmployeeDTO;

import java.util.ArrayList;
import java.util.List;

public record OrganizationDTO(Long id,
							  String name,
							  String address,
							  List<DepartmentDTO> departments,
							  List<EmployeeDTO> employees) {
	public OrganizationDTO(Long id, String name, String address) {
		this(id, name, address, new ArrayList<>(), new ArrayList<>());
	}
}
