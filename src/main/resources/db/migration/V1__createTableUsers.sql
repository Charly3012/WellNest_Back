create table users
(

    id       bigint       not null auto_increment,
    name   varchar(100) not null,
    nickname varchar(50)  not null unique,
    email    varchar(50)  not null unique,
    password varchar(200) not null,
    bornDate date,

    primary key (id)

);