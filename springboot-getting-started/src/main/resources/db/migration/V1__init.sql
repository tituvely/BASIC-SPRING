drop table if exists account;
drop sequence if exists hibernate_sequence;
create sequence hibernate_sequence start 1 increment 1;
create table account
(
    id       bigint not null,
    password varchar(255),
    username varchar(255),
    primary key (id)
);
