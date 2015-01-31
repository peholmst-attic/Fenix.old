package net.pkhapps.fenix.core.boundary.rest;

import net.pkhapps.fenix.core.entity.AbstractEntity;

import java.util.Objects;

/**
 * Base class for mappers that convert {@link net.pkhapps.fenix.core.entity.AbstractEntity entities} to
 * {@link net.pkhapps.fenix.core.boundary.rest.AbstractEntityDTO DTOs} and vice versa.
 */
public abstract class AbstractEntityDTOMapper<DTO extends AbstractEntityDTO, E extends AbstractEntity> {

    private final Class<DTO> dtoClass;

    private final Class<E> entityClass;

    protected AbstractEntityDTOMapper(Class<DTO> dtoClass, Class<E> entityClass) {
        this.dtoClass = dtoClass;
        this.entityClass = entityClass;
    }

    /**
     * Creates and returns a new, clean DTO instance.
     */
    protected DTO newDTO() {
        try {
            return dtoClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Could not create new DTO instance. This is a bug.", e);
        }
    }

    /**
     * Creates and returns a new, clean Entity instance.
     */
    protected E newEntity() {
        try {
            return entityClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Could not create new Entity instance. This is a bug.", e);
        }
    }

    /**
     * Creates and returns a DTO for the specified entity.
     */
    public DTO toDTO(E entity) {
        Objects.requireNonNull(entity);
        DTO dto = newDTO();
        dto.id = entity.getId();
        dto.version = entity.getOptLockVersion();
        populateDTO(entity, dto);
        return dto;
    }

    /**
     * Populates the specified DTO with data from the specified entity. The {@link net.pkhapps.fenix.core.entity.AbstractEntity#getId() ID}
     * and {@link net.pkhapps.fenix.core.entity.AbstractEntity#getOptLockVersion() version} properties do not need to be copied.
     */
    protected abstract void populateDTO(E source, DTO destination);
}
