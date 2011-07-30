package com.github.peholmst.fenix.common.util.dto;

@DataTransferObject(entityClasses = SecondTestEntity.class)
public class DTOForSecondTestEntity {

    @EntityIdentifier
    @EntityField(fieldName = SecondTestEntity.PROP_ID)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
