/*
 * Copyright (c) 2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pub.ihub.demo.rest.gateway;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pub.ihub.demo.rest.department.DepartmentDTO;
import pub.ihub.demo.rest.department.DepartmentExternalAPI;
import pub.ihub.demo.rest.employee.EmployeeDTO;
import pub.ihub.demo.rest.employee.EmployeeExternalAPI;
import pub.ihub.demo.rest.organization.OrganizationDTO;
import pub.ihub.demo.rest.organization.OrganizationExternalAPI;

/**
 * 网关接口
 * 这是网关接口
 *
 * @author liheng
 * @aaa aasada
 * @dddd asadasf
 * @see DepartmentExternalAPI
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GatewayManagement {

	private final DepartmentExternalAPI departmentExternalAPI;
	private final EmployeeExternalAPI employeeExternalAPI;
	private final OrganizationExternalAPI organizationExternalAPI;

	/**
	 * API: Get organization by id with departments
	 *
	 * @param id 组织 ID
	 * @return 组织
	 */
	@GetMapping("/organizations/{id}/with-departments")
	public OrganizationDTO apiOrganizationWithDepartments(@PathVariable("id") Long id) {
		return organizationExternalAPI.findByIdWithDepartments(id);
	}

	/**
	 * API: Get organization by id with departments and employees
	 *
	 * @param id 组织 ID
	 * @return 组织
	 */
	@GetMapping("/organizations/{id}/with-departments-and-employees")
	public OrganizationDTO apiOrganizationWithDepartmentsAndEmployees(@PathVariable("id") Long id) {
		return organizationExternalAPI.findByIdWithDepartmentsAndEmployees(id);
	}

	/**
	 * API: Post organization
	 *
	 * @param o 组织
	 * @return 组织
	 */
	@PostMapping("/organizations")
	public OrganizationDTO apiAddOrganization(@RequestBody OrganizationDTO o) {
		return organizationExternalAPI.add(o);
	}

	/**
	 * API: Post employee
	 *
	 * @param employee 员工
	 * @return 员工
	 */
	@PostMapping("/employees")
	public EmployeeDTO apiAddEmployee(@RequestBody EmployeeDTO employee) {
		return employeeExternalAPI.add(employee);
	}

	/**
	 * API: Get department by id with employees
	 *
	 * @param id 部门 ID
	 * @return 部门
	 */
	@GetMapping("/departments/{id}/with-employees")
	public DepartmentDTO apiDepartmentWithEmployees(@PathVariable("id") Long id) {
		return departmentExternalAPI.getDepartmentByIdWithEmployees(id);
	}

	/**
	 * API: Post department
	 *
	 * @param department 部门
	 * @return 部门
	 */
	@PostMapping("/departments")
	public DepartmentDTO apiAddDepartment(@RequestBody DepartmentDTO department) {
		return departmentExternalAPI.add(department);
	}
}
