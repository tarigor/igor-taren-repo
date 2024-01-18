create table sales
(
    id       int auto_increment
        primary key,
    buyer_id int  not null,
    adv_id   int  not null,
    date     date not null,
    constraint sales_id_uindex
        unique (id),
    constraint sales_advertisement_id_fk
        foreign key (adv_id) references advertisement (id),
    constraint sales_user_id_fk
        foreign key (buyer_id) references user (id)
);

