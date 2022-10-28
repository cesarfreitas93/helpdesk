create sequence t_historyuser_id_seq
    as integer;

alter sequence t_historyuser_id_seq owner to postgres;

create sequence t_sprint_id_seq1
    as integer;

alter sequence t_sprint_id_seq1 owner to postgres;

create table if not exists t_users
(
    id serial,
    username varchar(17) not null,
    password varchar(100) not null,
    config text not null,
    status integer default 0 not null,
    create_at timestamp,
    update_at timestamp,
    create_user integer,
    update_user integer,
    constraint t_users_pk
    primary key (id)
    );

comment on table t_users is 'Tabla de usuarios de la base de datos';

comment on column t_users.username is 'usuario del sistema';

comment on column t_users.password is 'contraseña del usuario';

comment on column t_users.config is 'configuracion del usuario en formato json';

comment on column t_users.status is '0: activo, 1: inactivo';

comment on column t_users.create_at is 'fecha de creacion';

comment on column t_users.update_at is 'fecha de modificacion';

comment on column t_users.create_user is 'usuario creacion';

comment on column t_users.update_user is 'usuario modificacion';

alter table t_users owner to postgres;

create table if not exists t_person
(
    id serial,
    name varchar(50) not null,
    doctype integer not null,
    docnumber varchar(20) not null,
    dateofbirth timestamp,
    id_user integer not null,
    constraint t_person_pk
    primary key (id),
    constraint t_person_t_user_id_fk
    foreign key (id_user) references t_users
    );

comment on table t_person is 'Tabla que contiene las personas registradas del sistema';

comment on column t_person.doctype is 'tipo de documento de identidad: 0 dni, 1 ce, 2 ruc';

comment on column t_person.docnumber is 'numero de documento de identidad';

comment on column t_person.dateofbirth is 'fecha de nacimiento';

alter table t_person owner to postgres;

create unique index if not exists t_user_pk_into_person
    on t_person (id_user);

create unique index if not exists t_users_username_uindex
    on t_users (username);

create table if not exists t_project
(
    id serial,
    name varchar(50) not null,
    description text,
    config text,
    files text,
    status integer default 0 not null,
    update_user integer,
    create_at timestamp,
    create_user integer,
    update_at timestamp,
    constraint t_project_pk
    primary key (id)
    );

comment on table t_project is 'Tabla de proyectos';

alter table t_project owner to postgres;

create table if not exists t_team
(
    id serial,
    name varchar(50),
    description text,
    status integer,
    create_at timestamp,
    update_at timestamp,
    create_user integer,
    update_user integer,
    constraint t_team_pk
    primary key (id)
    );

comment on table t_team is 'Tabla de equipos';

alter table t_team owner to postgres;

create table if not exists t_menber
(
    id serial,
    id_persona integer,
    id_team integer,
    status integer,
    create_at timestamp,
    update_at timestamp,
    create_user integer,
    update_user integer,
    constraint t_menber_pk
    primary key (id),
    constraint t_menber_t_person_id_fk
    foreign key (id_persona) references t_person,
    constraint t_menber_t_team_id_fk
    foreign key (id_team) references t_team
    );

alter table t_menber owner to postgres;

create table if not exists t_projxteam
(
    id serial,
    id_team integer,
    id_project integer,
    constraint t_projxteam_pk
    primary key (id),
    constraint t_projxteam_t_team_id_fk
    foreign key (id_team) references t_team,
    constraint t_projxteam_t_project_id_fk
    foreign key (id_project) references t_project
    );

alter table t_projxteam owner to postgres;

create table if not exists mt_cathu
(
    id serial,
    name varchar(15) not null,
    config text,
    constraint mt_cathu_pk
    primary key (id)
    );

comment on table mt_cathu is 'Tabla maestra categoria de historia de usuario';

alter table mt_cathu owner to postgres;

create table if not exists mt_statustask
(
    id serial,
    name varchar(20) not null,
    constraint mt_statustask_pk
    primary key (id)
    );

alter table mt_statustask owner to postgres;

create table if not exists t_task
(
    id serial,
    name varchar(50),
    description text,
    status integer default 1 not null,
    assignedto integer,
    files text,
    create_at integer,
    update_at integer,
    create_user integer,
    update_user integer,
    ticket integer,
    complete_at timestamp,
    complete_user integer,
    constraint t_task_pk
    primary key (id),
    constraint t_task_mt_statustask_id_fk
    foreign key (status) references mt_statustask
    );

alter table t_task owner to postgres;

create table if not exists t_sprint
(
    id integer default nextval('tesis.t_sprint_id_seq1'::regclass) not null,
    name varchar(100) not null,
    begin_date timestamp,
    end_date timestamp,
    status integer,
    create_at timestamp,
    update_at timestamp,
    create_user integer,
    update_user integer,
    constraint t_sprint_pk
    primary key (id)
    );

alter table t_sprint owner to postgres;

alter sequence t_sprint_id_seq1 owned by t_sprint.id;

create table if not exists t_ticketit
(
    id integer default nextval('tesis.t_historyuser_id_seq'::regclass) not null,
    name varchar(50) not null,
    category integer,
    weight integer,
    status integer default 1,
    create_at timestamp,
    update_at timestamp,
    complete_at timestamp,
    create_user integer,
    update_user integer,
    complete_user integer,
    assignedto integer,
    description text,
    id_sprint integer not null,
    id_project integer not null,
    constraint t_historyuser_pk
    primary key (id),
    constraint t_historyuser_mt_cathu_id_fk
    foreign key (category) references mt_cathu,
    constraint t_historyuser_mt_statustask_id_fk
    foreign key (status) references mt_statustask,
    constraint t_ticketit_t_sprint_id_fk
    foreign key (id_sprint) references t_sprint,
    constraint t_ticketit_t_project_id_fk
    foreign key (id_project) references t_project
    );

alter table t_ticketit owner to postgres;

alter sequence t_historyuser_id_seq owned by t_ticketit.id;

create unique index if not exists t_sprint_id_uindex
    on t_sprint (id);

create unique index if not exists t_sprint_name_uindex
    on t_sprint (name);

create table if not exists t_role
(
    id serial,
    name varchar(40) not null,
    constraint t_role_pk
    primary key (id)
    );

comment on table t_role is 'Tabla de roles del sistema';

alter table t_role owner to postgres;

create unique index if not exists t_role_id_uindex
    on t_role (id);

create unique index if not exists t_role_name_uindex
    on t_role (name);

create table if not exists it_role_user
(
    id_user integer not null,
    id_rol integer not null,
    constraint it_role_user_pk
    primary key (id_user, id_rol),
    constraint it_role_user_t_role_id_fk
    foreign key (id_rol) references t_role,
    constraint it_role_user_t_users_id_fk
    foreign key (id_user) references t_users
    );

alter table it_role_user owner to postgres;

create unique index if not exists it_role_user_id_user_id_rol_uindex
    on it_role_user (id_user, id_rol);

