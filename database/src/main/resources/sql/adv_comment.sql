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

