create table correspondence
(
    id        int auto_increment
        primary key,
    seller_id int  not null,
    buyer_id  int  not null,
    message   text null,
    constraint correspondence_id_uindex
        unique (id),
    constraint correspondence_user_fk
        foreign key (seller_id) references user (id),
    constraint correspondence_user_id_fk
        foreign key (buyer_id) references user (id)
);

