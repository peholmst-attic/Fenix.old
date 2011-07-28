package com.github.peholmst.fenix.entity.util;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2011-07-28T22:03:00.520+0300")
@StaticMetamodel(HistoricalEntityBase.class)
public class HistoricalEntityBase_ extends EntityBase_ {
	public static volatile SingularAttribute<HistoricalEntityBase, Long> masterId;
	public static volatile SingularAttribute<HistoricalEntityBase, Boolean> currentVersion;
	public static volatile SingularAttribute<HistoricalEntityBase, User> createdBy;
	public static volatile SingularAttribute<HistoricalEntityBase, Date> createdOn;
	public static volatile SingularAttribute<HistoricalEntityBase, Boolean> deleted;
	public static volatile SingularAttribute<HistoricalEntityBase, Long> revision;
}
