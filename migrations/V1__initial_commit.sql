create table otus.user
(
    id         int auto_increment primary key,
    login      varchar(255) not null,
    first_name varchar(255) not null,
    last_name  varchar(255) not null,
    age        int          not null,
    interests  json         null,
    city       varchar(255) not null,
    password   varchar(255) not null,
    gender     varchar(255) not null,
    constraint user_id_uindex
        unique (id),
    constraint user_login_uindex
        unique (login)
);