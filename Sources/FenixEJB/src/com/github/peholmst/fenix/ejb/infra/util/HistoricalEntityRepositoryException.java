package com.github.peholmst.fenix.ejb.infra.util;

import com.github.peholmst.fenix.entity.util.HistoricalEntityBase;

public class HistoricalEntityRepositoryException extends
        EntityRepositoryException {

    public static HistoricalEntityRepositoryException entityIsNotCurrentVersion(
            HistoricalEntityBase entity) {
        return null;
    }
}
