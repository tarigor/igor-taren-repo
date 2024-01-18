create table user
(
    id         int auto_increment
        primary key,
    first_name char(50)  not null,
    last_name  char(50)  not null,
    email      char(100) not null,
    user_type  char(20)  not null,
    password   char(255) not null,
    constraint user_email_uindex
        unique (email),
    constraint user_id_uindex
        unique (id)
);

