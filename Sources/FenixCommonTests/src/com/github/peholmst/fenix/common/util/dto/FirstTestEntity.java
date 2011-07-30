package com.github.peholmst.fenix.common.util.dto;

public class FirstTestEntity {

    public static final String PROP_ID = "id";
    public static final String PROP_BASIC_PROPERTY = "basicProperty";
    public static final String PROP_ENTITY_PROPERTY = "entityProperty";

    private long id;
    private String basicProperty;
    private SecondTestEntity entityProperty;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBasicProperty() {
        return basicProperty;
    }

    public void setBasicProperty(String basicProperty) {
        this.basicProperty = basicProperty;
    }

    public SecondTestEntity getEntityProperty() {
        return entityProperty;
    }

    public void setEntityProperty(SecondTestEntity entityProperty) {
        this.entityProperty = entityProperty;
    }
}