create table user
(
    id           bigint auto_increment
        primary key,
    first_name   varchar(50)  null,
    last_name    varchar(50)  null,
    company_name varchar(50)  null,
    email        varchar(100) not null,
    user_type    varchar(20)  not null,
    password     varchar(255) not null,
    constraint UK_ob8kqyqqgmefl0aco34akdtpe
        unique (email)
);

create table adv
(
    id          bigint auto_increment
        primary key,
    seller_id   bigint     not null,
    adv_message text       not null,
    priority    tinyint(1) not null,
    constraint adv_user_id_fk
        foreign key (seller_id) references user (id),
    constraint FKl47kyohgxskh7istodtwkcoj4
        foreign key (seller_id) references user (id)
);

create table adv_comment
(
    id                 bigint auto_increment
        primary key,
    adv_id             bigint       not null,
    comment_maker_name varchar(255) null,
    comment_text       text         null,
    constraint adv_comment_adv_id_fk
        foreign key (adv_id) references adv (id)
);

create table correspondence
(
    id        bigint auto_increment
        primary key,
    buyer_id  bigint not null,
    seller_id bigint not null,
    message   text   null,
    constraint correspondence_user_id_fk
        foreign key (buyer_id) references user (id),
    constraint correspondence_user_id_fk_2
        foreign key (seller_id) references user (id)
);

create table `order`
(
    id       bigint auto_increment
        primary key,
    adv_id   bigint      not null,
    buyer_id bigint      not null,
    date     datetime(6) not null,
    constraint order_adv_id_fk
        foreign key (adv_id) references adv (id),
    constraint order_user_id_fk
        foreign key (buyer_id) references user (id)
);

create table seller_rating
(
    id        bigint auto_increment
        primary key,
    seller_id bigint not null,
    rating    int    not null,
    constraint seller_rating_user_id_fk
        foreign key (seller_id) references user (id)
);