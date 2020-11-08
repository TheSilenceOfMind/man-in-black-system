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

------------------------------------------------------
----------------------Documents-----------------------
------------------------------------------------------

------------------------------------------------------
----------------------Authentication------------------
------------------------------------------------------
insert into mib_role(role_name) values ('Passporter');
insert into mib_role(role_name) values ('Researcher');
insert into mib_role(role_name) values ('OpAgent');
insert into mib_role(role_name) values ('Lawyer');
insert into mib_role(role_name) values ('HR');
insert into mib_role(role_name) values ('Technologist');

insert into mib_user(username, password) values ('kirill', '$2y$11$M0aiTmk9sdzH6rtRctvoauFcvEu6y9L6QX0cBIw6rcZ.WWq9wcCYy');

insert into mib_user_role(user_id, role_id) values (1, 1);