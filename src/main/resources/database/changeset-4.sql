-- drops
ALTER TABLE motivation_events
DROP CONSTRAINT IF EXISTS motivation_events_users_fkey;

ALTER TABLE motivation_events
DROP COLUMN IF EXISTS user_id;

DROP TABLE IF EXISTS public.users_roles;

DROP SEQUENCE IF EXISTS public.users_roles_id_seq;

DROP TABLE IF EXISTS public.users;

DROP SEQUENCE IF EXISTS public.users_id_seq;

DROP TABLE IF EXISTS public.roles;

DROP SEQUENCE IF EXISTS public.roles_id_seq;

-- creates

CREATE SEQUENCE public.users_id_seq
    INCREMENT 1
    START WITH 1;

CREATE TABLE IF NOT EXISTS public.users
(
    user_id integer NOT NULL DEFAULT nextval('users_id_seq'),
    name varchar NOT NULL,
    password varchar NOT NULL DEFAULT 'password',
    CONSTRAINT users_pkey PRIMARY KEY (user_id)
);

CREATE SEQUENCE IF NOT EXISTS public.roles_id_seq
    INCREMENT 1
    START WITH 1;

CREATE TABLE IF NOT EXISTS public.roles
(
    role_id integer NOT NULL DEFAULT nextval('roles_id_seq'),
    name varchar NOT NULL,
    CONSTRAINT roles_pkey PRIMARY KEY (role_id)
);

CREATE SEQUENCE IF NOT EXISTS public.users_roles_id_seq
    INCREMENT 1
    START WITH 1;

CREATE TABLE IF NOT EXISTS public.users_roles
(
    user_role_id integer NOT NULL DEFAULT nextval('users_roles_id_seq'),
    user_id integer NOT NULL,
    role_id integer NOT NULL,
    CONSTRAINT users_roles_pkey PRIMARY KEY (user_role_id),
    CONSTRAINT users_roles_roles_fkey
          FOREIGN KEY(role_id)
    	  REFERENCES public.roles(role_id),
    CONSTRAINT users_roles_users_fkey
              FOREIGN KEY(user_id)
        	  REFERENCES public.users(user_id)
);

insert into users (name, password) values ('admin', '$2a$04$MjaRwMILzVekKVysvndUcuObMsDJK4EsiJJTZ1glpaYzf5VUmbJR6');
insert into roles (name) values ('ROLE_ADMIN');
insert into roles (name) values ('ROLE_USER');
insert into users_roles (user_id,role_id) values(1,1);
insert into users_roles (user_id,role_id) values(1,2);

ALTER TABLE motivation_events
ADD user_id integer NOT NULL DEFAULT 1;

ALTER TABLE motivation_events
ADD CONSTRAINT motivation_events_users_fkey
    FOREIGN KEY(user_id)
        REFERENCES public.users(user_id)
        ON DELETE CASCADE;





