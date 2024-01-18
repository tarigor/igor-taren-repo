create table seller_rating
(
    id        int auto_increment
        primary key,
    seller_id int not null,
    rating    int not null,
    constraint seller_rating_id_uindex
        unique (id),
    constraint seller_rating_user_id_fk
        foreign key (seller_id) references user (id)
);

