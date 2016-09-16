create database app;

create table applicant
(
    id int not null auto_increment primary key,
    gender varchar(10) not null,
    name varchar(20) not null,
    position varchar(20) not null,
    skills varchar(255) not null,
    email varchar(20) not null
);