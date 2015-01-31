package net.pkhapps.fenix.core.boundary.rest.dto;

import net.pkhapps.fenix.core.boundary.rest.AbstractEntityDTOMapper;
import net.pkhapps.fenix.core.entity.FireDepartment;
import org.springframework.stereotype.Component;

/**
 * DTO mapper for {@link net.pkhapps.fenix.core.boundary.rest.dto.FireDepartmentDTO} and {@link net.pkhapps.fenix.core.entity.FireDepartment}.
 */
@Component
public class FireDepartmentDTOMapper extends AbstractEntityDTOMapper<FireDepartmentDTO, FireDepartment> {

    public FireDepartmentDTOMapper() {
        super(FireDepartmentDTO.class, FireDepartment.class);
    }

    @Override
    protected void populateDTO(FireDepartment source, FireDepartmentDTO destination) {
        destination.name = source.getName();
        destination.enabled = source.isEnabled();
    }

    @Override
    protected void populateEntity(FireDepartmentDTO source, FireDepartment destination) {
        throw new UnsupportedOperationException("Not implemented yet"); // TODO Implement me
    }
}
