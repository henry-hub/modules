package pub.ihub.demo.rest.organization;

import pub.ihub.demo.rest.department.DepartmentDTO;
import pub.ihub.demo.rest.employee.EmployeeDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * 组织 DTO
 * <p>
 * 组织数据传输对象
 *
 * @param id      组织 ID
 * @param name    组织名称
 * @param address 组织地址
 */
public record OrganizationDTO(Long id,
							  String name,
							  String address,
							  List<DepartmentDTO> departments,
							  List<EmployeeDTO> employees) {
	public OrganizationDTO(Long id, String name, String address) {
		this(id, name, address, new ArrayList<>(), new ArrayList<>());
	}
}
