package pub.ihub.demo.rest.department.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import pub.ihub.demo.rest.department.DepartmentDTO;
import pub.ihub.demo.rest.department.model.Department;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DepartmentMapper {
	DepartmentDTO departmentToDepartmentDTO(Department department);

	Department departmentDTOToDepartment(DepartmentDTO departmentDTO);
}
