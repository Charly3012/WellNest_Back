create table users
(

    idUser bigint not null auto_increment,
    name   varchar(100) not null,
    nickname varchar(50)  not null unique,
    email    varchar(50)  not null unique,
    password varchar(300) not null,
    bornDate date,

    primary key (idUser)

);