package pub.ihub.demo.rest.organization.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import pub.ihub.demo.rest.organization.OrganizationDTO;
import pub.ihub.demo.rest.organization.model.Organization;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrganizationMapper {
	OrganizationDTO organizationToOrganizationDTO(Organization organization);

	Organization organizationDTOToOrganization(OrganizationDTO organizationDTO);
}
