
CREATE DATABASE Credit;
create table Credit
(
    id         bigint auto_increment
        primary key,
    creditName varchar(255) null,
    customerId bigint       null,
    value      double       null
);

