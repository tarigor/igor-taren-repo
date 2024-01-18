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

create table adv_comment
(
    id                 int auto_increment
        primary key,
    adv_id             int  not null,
    comment_maker_name text null,
    comment_text       text null,
    constraint adv_comment_id_uindex
        unique (id),
    constraint adv_comment_advertisement_id_fk
        foreign key (adv_id) references advertisement (id)
);