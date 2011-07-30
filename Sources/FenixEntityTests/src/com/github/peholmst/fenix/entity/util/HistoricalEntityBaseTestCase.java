package com.github.peholmst.fenix.entity.util;

import java.util.Date;

public abstract class HistoricalEntityBaseTestCase<E extends HistoricalEntityBase>
        extends EntityBaseTestCase<E> {

    @Override
    protected E createEntityToPersist() {
        E entity = super.createEntityToPersist();
        entity.setLastUpdatedBy("JoeCool");
        entity.setLastUpdatedOn(new Date());
        return entity;
    }
}
