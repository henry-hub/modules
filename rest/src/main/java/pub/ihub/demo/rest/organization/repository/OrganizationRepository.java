package pub.ihub.demo.rest.organization.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pub.ihub.demo.rest.organization.OrganizationDTO;
import pub.ihub.demo.rest.organization.model.Organization;

public interface OrganizationRepository extends CrudRepository<Organization, Long> {

    @Query("""
            SELECT new pub.ihub.demo.rest.organization.OrganizationDTO(o.id, o.name, o.address)
            FROM Organization o
            WHERE o.id = :id
            """)
	OrganizationDTO findDTOById(Long id);
}
