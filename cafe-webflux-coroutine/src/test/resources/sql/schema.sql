-- auto-generated definition
create table coffee
(
    id            bigint auto_increment
        primary key,
    name          varchar(100) not null,
    price         int          not null,
    thumbnail_url varchar(300) not null,
    description   varchar(200) null
);
