package com.github.peholmst.fenix.common.util.dto;

import java.util.Date;

public class SecondTestEntity {

    public static final String PROP_ID = "id";
    public static final String PROP_ANOTHER_BASIC_PROPERTY = "anotherBasicProperty";

    private long id;
    private Date anotherBasicProperty;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getAnotherBasicProperty() {
        return anotherBasicProperty;
    }

    public void setAnotherBasicProperty(Date anotherBasicProperty) {
        this.anotherBasicProperty = anotherBasicProperty;
    }

}