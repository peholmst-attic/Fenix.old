package com.github.peholmst.fenix.common.util.dto;

import java.util.Date;

@DataTransferObject(entityClasses = { FirstTestEntity.class,
        SecondTestEntity.class })
public class DTOForMultipleEntities {

    @EntityIdentifier
    @EntityField(entityClass = FirstTestEntity.class,
            fieldName = FirstTestEntity.PROP_ID)
    private long firstIdentifier;

    @EntityIdentifier
    @EntityField(entityClass = SecondTestEntity.class,
            fieldName = SecondTestEntity.PROP_ID)
    private long secondIdentifier;

    @EntityField(entityClass = FirstTestEntity.class,
            fieldName = FirstTestEntity.PROP_BASIC_PROPERTY)
    private String firstBasicField;

    @EntityField(entityClass = SecondTestEntity.class,
            fieldName = SecondTestEntity.PROP_ANOTHER_BASIC_PROPERTY)
    private Date secondBasicField;

    public long getFirstIdentifier() {
        return firstIdentifier;
    }

    public void setFirstIdentifier(long firstIdentifier) {
        this.firstIdentifier = firstIdentifier;
    }

    public long getSecondIdentifier() {
        return secondIdentifier;
    }

    public void setSecondIdentifier(long secondIdentifier) {
        this.secondIdentifier = secondIdentifier;
    }

    public String getFirstBasicField() {
        return firstBasicField;
    }

    public void setFirstBasicField(String firstBasicField) {
        this.firstBasicField = firstBasicField;
    }

    public Date getSecondBasicField() {
        return secondBasicField;
    }

    public void setSecondBasicField(Date secondBasicField) {
        this.secondBasicField = secondBasicField;
    }

}