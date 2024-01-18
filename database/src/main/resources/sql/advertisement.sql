create table advertisement
(
    id          int auto_increment
        primary key,
    seller_id   int  not null,
    adv_message text not null,
    priority    int  not null,
    constraint advertisement_id_uindex
        unique (id),
    constraint advertisement_user_id_fk
        foreign key (seller_id) references user (id)
);

