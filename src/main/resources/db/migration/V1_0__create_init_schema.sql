------------------------------------------------------
----------------------Authentication------------------
------------------------------------------------------
create table mib_user
(
    user_id             serial primary key,
    username            varchar(50) unique not null,
    password            varchar(100)       not null,
    disabled            boolean default false,
    account_expired     boolean default false,
    account_locked      boolean default false,
    credentials_expired boolean default false
);

create table mib_role
(
    role_id   serial primary key,
    role_name varchar(50)
);

create table mib_user_role
(
    user_role_id serial primary key,
    user_id      integer references mib_user (user_id),
    role_id      integer references mib_role (role_id)
);

create table mib_alien_race
(
    race_id serial primary key,
    name    varchar(100) unique not null
);

create table mib_alien_passport
(
    passport_id         serial primary key,
    name                varchar(100) unique not null,
    home_planet         varchar(100) not null,
    description         varchar(100) not null,
    id_race             integer references mib_alien_race (race_id)
);

create table mib_source_technology
(
    source_id serial primary key,
    value    varchar(100) unique not null
);

create table mib_technology
(
    technology_id       serial primary key,
    name                varchar(100) unique not null,
    use                 varchar(100) not null,
    description         varchar(100) not null,
    id_race             integer references mib_alien_race (race_id),
    id_source           integer references mib_source_technology (source_id)
);

create table mib_act_detentions
(
    act_detention_id    serial primary key,
    scene               varchar(100) not null,
    description         varchar(100) not null,
    id_user_agent       integer references mib_user (user_id),
    id_guilty_alien     integer references mib_alien_passport (passport_id)
);

create table mib_nation
(
    nation_id serial primary key,
    name    varchar(100) unique not null
);

create table mib_type_earth_documents
(
    type_earth_document_id serial primary key,
    type    varchar(100) unique not null
);

create table mib_earth_document
(
    earth_document_id   serial primary key,
    earth_alien_name    varchar(100) not null,
    description         varchar(100) not null,
    id_nation           integer references mib_nation (nation_id),
    id_type_document    integer references mib_type_earth_documents (type_earth_document_id),
    id_alien            integer references mib_alien_passport (passport_id)
);

------------------------------------------------------
----------------------Documents-----------------------
------------------------------------------------------

------------------------------------------------------
----------------------Authentication------------------
------------------------------------------------------
insert into mib_role(role_name) values ('PASSPORTER');
insert into mib_role(role_name) values ('RESEARCHER');
insert into mib_role(role_name) values ('OP_AGENT');
insert into mib_role(role_name) values ('LAWYER');
insert into mib_role(role_name) values ('HR');
insert into mib_role(role_name) values ('TECHNOLOGIST');

insert into mib_user(username, password) values ('k1', '$2y$11$M0aiTmk9sdzH6rtRctvoauFcvEu6y9L6QX0cBIw6rcZ.WWq9wcCYy');
insert into mib_user(username, password) values ('k2', '$2y$11$M0aiTmk9sdzH6rtRctvoauFcvEu6y9L6QX0cBIw6rcZ.WWq9wcCYy');
insert into mib_user(username, password) values ('k3', '$2y$11$M0aiTmk9sdzH6rtRctvoauFcvEu6y9L6QX0cBIw6rcZ.WWq9wcCYy');
insert into mib_user(username, password) values ('k4', '$2y$11$M0aiTmk9sdzH6rtRctvoauFcvEu6y9L6QX0cBIw6rcZ.WWq9wcCYy');
insert into mib_user(username, password) values ('k5', '$2y$11$M0aiTmk9sdzH6rtRctvoauFcvEu6y9L6QX0cBIw6rcZ.WWq9wcCYy');
insert into mib_user(username, password) values ('k6', '$2y$11$M0aiTmk9sdzH6rtRctvoauFcvEu6y9L6QX0cBIw6rcZ.WWq9wcCYy');

insert into mib_user_role(user_id, role_id) values (1, 1);
insert into mib_user_role(user_id, role_id) values (2, 2);
insert into mib_user_role(user_id, role_id) values (3, 3);
insert into mib_user_role(user_id, role_id) values (4, 4);
insert into mib_user_role(user_id, role_id) values (5, 5);
insert into mib_user_role(user_id, role_id) values (6, 6);

insert into mib_alien_race(name) values ('123');
insert into mib_alien_race(name) values ('456');
insert into mib_alien_race(name) values ('789');

insert into mib_alien_passport(name, home_planet, description, id_race) values ('afsa', 'dsfd', 'hted', 1);
insert into mib_alien_passport(name, home_planet, description, id_race) values ('FSFA', 'sgrwb ', 'sdgh', 2);
insert into mib_alien_passport(name, home_planet, description, id_race) values ('bfdnd', 'yrwfgvv', 'vjhr', 3);

insert into mib_source_technology(value) values ('321');
insert into mib_source_technology(value) values ('654');
insert into mib_source_technology(value) values ('987');

insert into mib_technology(name, use, description, id_race, id_source) values ('sdfdbdgqad', 'gwvsdgdsf', 'sgeqvdseq', 1, 3);
insert into mib_technology(name, use, description, id_race, id_source) values ('rahtaebdfd', 'dsgwrbds ', 'dsgwsvxvd', 2, 2);
insert into mib_technology(name, use, description, id_race, id_source) values ('hyjghm', 'bfbetjdfbv', 'rtmyjdfbwrbw', 3, 1);

insert into mib_act_detentions(scene, description, id_user_agent, id_guilty_alien) values ('dsdsbryn', ',ln,lneb', 4, 1);
insert into mib_act_detentions(scene, description, id_user_agent, id_guilty_alien) values ('kmytjmtn', 'jy5jnrgdxv', 4, 2);
insert into mib_act_detentions(scene, description, id_user_agent, id_guilty_alien) values ('tijrgndsn', 'swgy4bdfdrthe', 4, 3);

insert into mib_nation(name) values ('111');
insert into mib_nation(name) values ('222');
insert into mib_nation(name) values ('333');

insert into mib_type_earth_documents(type) values ('999');
insert into mib_type_earth_documents(type) values ('888');
insert into mib_type_earth_documents(type) values ('777');

insert into mib_earth_document(earth_alien_name, description, id_nation, id_type_document, id_alien) values ('hjtdbsvwfbhn', 'bavneg', 1, 3, 1);
insert into mib_earth_document(earth_alien_name, description, id_nation, id_type_document, id_alien) values ('hdetjyyklyh', 'asqqnwnmy', 2, 2, 2);
insert into mib_earth_document(earth_alien_name, description, id_nation, id_type_document, id_alien) values ('nt,lukghvmjr', ',l,cxfsj', 3, 1, 3);