package pub.ihub.demo.rest.department;

public interface DepartmentExternalAPI {

    DepartmentDTO getDepartmentByIdWithEmployees(Long id);
    DepartmentDTO add(DepartmentDTO department);
}
