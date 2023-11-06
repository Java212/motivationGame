package ru.inspired.notes;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Note.class)
public abstract class Note_ {

	public static volatile SingularAttribute<Note, LocalDateTime> createdTime;
	public static volatile SingularAttribute<Note, Integer> id;
	public static volatile SingularAttribute<Note, String> text;

	public static final String CREATED_TIME = "createdTime";
	public static final String ID = "id";
	public static final String TEXT = "text";

}

