package ru.inspired.model;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DailyStatus.class)
public abstract class DailyStatus_ {

	public static volatile SingularAttribute<DailyStatus, LocalDate> date;
	public static volatile SingularAttribute<DailyStatus, Integer> calculationScore;
	public static volatile SingularAttribute<DailyStatus, Integer> id;
	public static volatile SingularAttribute<DailyStatus, CompletionState> state;
	public static volatile SingularAttribute<DailyStatus, MotivationEvent> motivationEvent;

	public static final String DATE = "date";
	public static final String CALCULATION_SCORE = "calculationScore";
	public static final String ID = "id";
	public static final String STATE = "state";
	public static final String MOTIVATION_EVENT = "motivationEvent";

}

