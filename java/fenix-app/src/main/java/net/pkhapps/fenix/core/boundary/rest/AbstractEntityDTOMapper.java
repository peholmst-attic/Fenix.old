package net.pkhapps.fenix.core.boundary.rest;

import net.pkhapps.fenix.core.entity.AbstractEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
     * Converts the specified stream of entities to a stream of DTOs.
     */
    public Stream<DTO> toDTOs(Stream<E> entities) {
        return entities.map(this::toDTO);
    }

    /**
     * Converts the specified list of entities to a list of DTOs.
     */
    public List<DTO> toDTOs(List<E> entities) {
        return toDTOs(entities.stream()).collect(Collectors.toList());
    }

    /**
     * Converts the specified page of entities to a page of DTOs.
     */
    public Page<DTO> toDTOs(Page<E> page) {
        return new PageImpl<>(toDTOs(page.getContent()),
                new PageRequest(page.getNumber(), page.getSize(), page.getSort()),
                page.getTotalElements());
    }

    /**
     * Populates the specified DTO with data from the specified entity. The {@link net.pkhapps.fenix.core.entity.AbstractEntity#getId() ID}
     * and {@link net.pkhapps.fenix.core.entity.AbstractEntity#getOptLockVersion() version} properties do not need to be copied.
     */
    protected abstract void populateDTO(E source, DTO destination);
}
