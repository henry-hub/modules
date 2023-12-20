package pub.ihub.demo.rest.department;


import pub.ihub.demo.rest.employee.EmployeeDTO;

import java.util.ArrayList;
import java.util.List;

public record DepartmentDTO(Long id,
							Long organizationId,
							String name,
							List<EmployeeDTO> employees) {
	public DepartmentDTO(Long id, Long organizationId, String name) {
		this(id, organizationId, name, new ArrayList<>());
	}
}
