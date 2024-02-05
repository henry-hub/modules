package pub.ihub.demo.rest.employee.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import pub.ihub.demo.rest.employee.EmployeeDTO;
import pub.ihub.demo.rest.employee.model.Employee;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmployeeMapper {
	EmployeeDTO employeeToEmployeeDTO(Employee employee);

	Employee employeeDTOToEmployee(EmployeeDTO employeeDTO);
}
