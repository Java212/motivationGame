-- drops
DROP TABLE IF EXISTS public.notes;

DROP SEQUENCE IF EXISTS public.notes_id_seq;

-- creates

CREATE SEQUENCE public.notes_id_seq
    INCREMENT 1
    START WITH 1;

CREATE TABLE IF NOT EXISTS public.notes
(
    note_id integer NOT NULL DEFAULT nextval('notes_id_seq'),
    text varchar NOT NULL,
    date_time timestamp without time zone default CURRENT_TIMESTAMP,
    CONSTRAINT notes_pkey PRIMARY KEY (note_id)
);

insert into notes(text) values ('test message: hello from liquibase');