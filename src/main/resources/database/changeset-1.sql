-- drops

DROP TABLE IF EXISTS notes;
DROP SEQUENCE IF EXISTS notes_id_seq;

--creates

CREATE SEQUENCE public.notes_id_seq
INCREMENT 1
START WITH 1;

CREATE TABLE NOT EXISTS notes (
    notes_id integer NOT NULL DEFAULT nextval('notes_id_seq'),
    text varchar NOT NULL,
    data_time timestamp without time zone default CURRENT_TIMESTAMP,
    CONSTRAINT notes_pkey PRIMARY KEY (notes_id)
);

