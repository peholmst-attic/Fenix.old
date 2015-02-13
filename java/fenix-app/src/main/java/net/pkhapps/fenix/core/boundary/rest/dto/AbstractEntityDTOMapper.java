package net.pkhapps.fenix.core.boundary.rest.dto;

import net.pkhapps.fenix.core.entity.AbstractEntity;
import net.pkhapps.fenix.core.validation.ConflictException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Base class for mappers that convert {@link net.pkhapps.fenix.core.entity.AbstractEntity entities} to
 * {@link AbstractEntityDTO DTOs} and vice versa.
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
     * Creates and returns an entity for the specified DTO.
     */
    public E toEntity(DTO dto) {
        Objects.requireNonNull(dto);
        E entity = newEntity();
        entity.setId(dto.id);
        entity.setOptLockVersion(dto.version);
        try {
            populateEntity(dto, entity);
        } catch (ConflictException e) {
            throw new RuntimeException("An optimistic locking conflict has been detected while saving a new object. This should never happen.", e);
        }
        return entity;
    }

    /**
     * Populates the existing entity with data from the DTO. This method also enforces optimistic locking.
     *
     * @throws net.pkhapps.fenix.core.validation.ConflictException if the version numbers of both objects are present and do not match.
     */
    public E toExistingEntity(DTO dto, E entity) throws ConflictException {
        Objects.requireNonNull(dto);
        Objects.requireNonNull(entity);
        Long expectedVersion = dto.version;
        Long actualVersion = entity.getOptLockVersion();
        if (expectedVersion != null && actualVersion != null && !expectedVersion.equals(actualVersion)) {
            throw new ConflictException("Expected version: " + expectedVersion + ", was: " + actualVersion);
        }
        populateEntity(dto, entity);
        return entity;
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
     * Converts the specified stream of DTOs to a stream of entities.
     */
    public Stream<E> toEntities(Stream<DTO> dtos) {
        return dtos.map(this::toEntity);
    }

    /**
     * Converts the specified list of DTOs to a list of entities.
     */
    public List<E> toEntities(List<DTO> dtos) {
        return toEntities(dtos.stream()).collect(Collectors.toList());
    }

    /**
     * TODO Document me
     *
     * @param source
     * @param destination
     * @return
     */
    public Stream<E> merge(Stream<DTO> source, Stream<E> destination) throws ConflictException {
        throw new UnsupportedOperationException("Not implemented yet"); // TODO Implement me!
    }

    /**
     * TODO Document me
     *
     * @param source
     * @param destination
     * @return
     */
    public List<E> merge(List<DTO> source, List<E> destination) throws ConflictException {
        return merge(source.stream(), destination.stream()).collect(Collectors.toList());
    }


    /**
     * Populates the specified DTO with data from the specified entity. The {@link net.pkhapps.fenix.core.entity.AbstractEntity#getId() ID}
     * and {@link net.pkhapps.fenix.core.entity.AbstractEntity#getOptLockVersion() version} properties do not need to be copied.
     *
     * @throws java.lang.UnsupportedOperationException if this mapper is a one-way converter from DTO to entity
     */
    protected abstract void populateDTO(E source, DTO destination);

    /**
     * Populates the specified entity with data from the specified DTO. The {@link net.pkhapps.fenix.core.entity.AbstractEntity#getId() ID}
     * and {@link net.pkhapps.fenix.core.entity.AbstractEntity#getOptLockVersion() version} properties do not need to be copied.
     *
     * @throws java.lang.UnsupportedOperationException             if this mapper is a one-way converter from entity to DTO
     * @throws net.pkhapps.fenix.core.validation.ConflictException if there is a conflict while populating any child entities.
     */
    protected abstract void populateEntity(DTO source, E destination) throws ConflictException;
}
