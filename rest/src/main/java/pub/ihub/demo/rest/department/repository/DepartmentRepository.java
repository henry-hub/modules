package pub.ihub.demo.rest.department.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pub.ihub.demo.rest.department.DepartmentDTO;
import pub.ihub.demo.rest.department.model.Department;

import java.util.List;

public interface DepartmentRepository extends CrudRepository<Department, Long> {

    @Query("""
           SELECT new pub.ihub.demo.rest.department.DepartmentDTO(d.id, d.organizationId, d.name)
           FROM Department d
           WHERE d.id = :id
           """)
	DepartmentDTO findDTOById(Long id);

    @Query("""
           SELECT new pub.ihub.demo.rest.department.DepartmentDTO(d.id, d.organizationId, d.name)
           FROM Department d
           WHERE d.organizationId = :organizationId
           """)
    List<DepartmentDTO> findByOrganizationId(Long organizationId);

    void deleteByOrganizationId(Long organizationId);
}
