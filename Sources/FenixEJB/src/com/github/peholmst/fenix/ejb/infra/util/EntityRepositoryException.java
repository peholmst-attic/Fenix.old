package com.github.peholmst.fenix.ejb.infra.util;

import com.github.peholmst.fenix.entity.util.EntityBase;

public class EntityRepositoryException extends RuntimeException {

    public static EntityRepositoryException entityNotFound(EntityBase entity) {
        return null;
    }

}
