package com.github.peholmst.fenix.common.util.dto;

@DataTransferObject(entityClasses = FirstTestEntity.class)
public class DTOForFirstTestEntity {

    @EntityIdentifier
    @EntityField(fieldName = FirstTestEntity.PROP_ID)
    private long id;

    @EntityField(fieldName = FirstTestEntity.PROP_BASIC_PROPERTY)
    private String basicField;

    @EntityField(fieldName = FirstTestEntity.PROP_ENTITY_PROPERTY)
    private DTOForSecondTestEntity entityField;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBasicField() {
        return basicField;
    }

    public void setBasicField(String basicField) {
        this.basicField = basicField;
    }

    public DTOForSecondTestEntity getEntityField() {
        return entityField;
    }

    public void setEntityField(DTOForSecondTestEntity entityField) {
        this.entityField = entityField;
    }
}