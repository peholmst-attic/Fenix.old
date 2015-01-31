package net.pkhapps.fenix.core.boundary.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Base class for DTOs that represent entities.
 */
public abstract class AbstractEntityDTO {

    @JsonProperty("id")
    public Long id;

    @JsonProperty("version")
    public Long version;
}
