package com.github.peholmst.fenix.entity.util;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2011-07-26T20:41:17.056+0300")
@StaticMetamodel(HistoricalEntityBase.class)
public class HistoricalEntityBase_ extends EntityBase_ {
	public static volatile SingularAttribute<HistoricalEntityBase, String> createdBy;
	public static volatile SingularAttribute<HistoricalEntityBase, Date> createdOn;
}
