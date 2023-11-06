package ru.inspired.model;

import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, String> password;
	public static volatile SetAttribute<User, Role> roles;
	public static volatile SingularAttribute<User, String> name;
	public static volatile SingularAttribute<User, Integer> id;
	public static volatile ListAttribute<User, MotivationEvent> events;

	public static final String PASSWORD = "password";
	public static final String ROLES = "roles";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String EVENTS = "events";

}

