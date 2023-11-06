package ru.inspired.model;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MotivationEvent.class)
public abstract class MotivationEvent_ {

	public static volatile SingularAttribute<MotivationEvent, Integer> bonus;
	public static volatile SingularAttribute<MotivationEvent, Integer> fee;
	public static volatile SingularAttribute<MotivationEvent, String> description;
	public static volatile SingularAttribute<MotivationEvent, Integer> id;
	public static volatile SingularAttribute<MotivationEvent, User> user;

	public static final String BONUS = "bonus";
	public static final String FEE = "fee";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";
	public static final String USER = "user";

}

