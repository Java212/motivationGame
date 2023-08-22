-- drops
DROP TABLE IF EXISTS public.motivation_events;

DROP SEQUENCE IF EXISTS public.motivation_events_id_seq;

DROP TABLE IF EXISTS public.daily_statuses;

DROP SEQUENCE IF EXISTS public.daily_statuses_id_seq;

-- creates

CREATE SEQUENCE public.motivation_events_id_seq
    INCREMENT 1
    START WITH 1;

CREATE TABLE IF NOT EXISTS public.motivation_events
(
    motivation_event_id integer NOT NULL DEFAULT nextval('motivation_events_id_seq'),
    text varchar NOT NULL,
    bonus integer NOT NULL,
    fee integer NOT NULL DEFAULT 0,
    CONSTRAINT motivation_events_pkey PRIMARY KEY (motivation_event_id)
);

CREATE SEQUENCE IF NOT EXISTS public.daily_statuses_id_seq
    INCREMENT 1
    START WITH 1;


CREATE TABLE IF NOT EXISTS public.daily_statuses
(
    daily_status_id integer NOT NULL DEFAULT nextval('daily_statuses_id_seq'),
    note varchar,
    date_of_check  date NOT NULL DEFAULT CURRENT_DATE,
    motivation_event_id integer NOT NULL,
    calculated_score integer NOT NULL,
    completion_state varchar NOT NULL,
    CONSTRAINT daily_statuses_pkey PRIMARY KEY (daily_status_id),
     CONSTRAINT daily_statuses_motivation_events_fkey
          FOREIGN KEY(motivation_event_id)
    	  REFERENCES public.motivation_events(motivation_event_id)
    	  ON DELETE CASCADE
);

insert into motivation_events (text,bonus) values ('Сходить в качалку', 5);
insert into motivation_events (text,bonus) values ('Сделать домашку', 5);



