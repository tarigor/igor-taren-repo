create table room
(
    id           bigint auto_increment
        primary key,
    capacity     int         not null,
    price        double      not null,
    room_status  varchar(50) not null,
    stars_rating int         not null,
    constraint room_room_id_uindex
        unique (id)
);

create table guest
(
    id         bigint auto_increment
        primary key,
    first_name varchar(30) not null,
    last_name  varchar(30) not null,
    constraint guest_guest_id_uindex
        unique (id)
);

create table room_service
(
    id           bigint auto_increment
        primary key,
    service_type varchar(40) not null,
    price        double      not null,
    constraint room_service_room_service_id_uindex
        unique (id)
);

create table guest_service
(
    id                      bigint auto_increment
        primary key,
    guest_id                bigint not null,
    room_service_id         bigint not null,
    room_service_order_date date   not null,
    constraint guest_service_guest_service_id_uindex
        unique (id),
    constraint guest_service_guest_guest_id_fk
        foreign key (guest_id) references guest (id)
            on update cascade on delete cascade,
    constraint guest_service_room_service_room_service_id_fk
        foreign key (room_service_id) references room_service (id)
            on update cascade on delete cascade
);

create table booking
(
    id             bigint auto_increment
        primary key,
    guest_id       bigint not null,
    room_id        bigint not null,
    check_in_date  date   not null,
    check_out_date date   not null,
    constraint booking_booking_id_uindex
        unique (id),
    constraint booking_guest_guest_id_fk
        foreign key (guest_id) references guest (id)
            on update cascade on delete cascade,
    constraint booking_room_room_id_fk
        foreign key (room_id) references room (id)
            on update cascade on delete cascade
);