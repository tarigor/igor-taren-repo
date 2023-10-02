create table HOTEL.PUBLIC.GUEST
(
    GUEST_ID   BIGINT auto_increment,
    FIRST_NAME CHARACTER VARYING(30) not null,
    LAST_NAME  CHARACTER VARYING(30) not null
);

create unique index HOTEL.PUBLIC.GUEST_ID_UINDEX
    on HOTEL.PUBLIC.GUEST (GUEST_ID);

alter table HOTEL.PUBLIC.GUEST
    add constraint GUEST_PK
        primary key (GUEST_ID);

create table HOTEL.PUBLIC.ROOM
(
    ROOM_ID      BIGINT auto_increment,
    CAPACITY     INTEGER               not null,
    PRICE        DOUBLE PRECISION      not null,
    ROOM_STATUS  CHARACTER VARYING(50) not null,
    STARS_RATING INTEGER               not null,
    constraint ROOM_PK
        primary key (ROOM_ID)
);

create unique index HOTEL.PUBLIC.ROOM_ROOM_ID_UINDEX
    on HOTEL.PUBLIC.ROOM (ROOM_ID);

create table HOTEL.PUBLIC.ROOM_SERVICE
(
    ROOM_SERVICE_ID BIGINT auto_increment,
    SERVICE_TYPE    CHARACTER VARYING(40) not null,
    PRICE           DOUBLE PRECISION      not null,
    constraint ROOM_SERVICE_PK
        primary key (ROOM_SERVICE_ID)
);

create unique index HOTEL.PUBLIC.ROOM_SERVICE_ROOM_SERVICE_ID_UINDEX
    on HOTEL.PUBLIC.ROOM_SERVICE (ROOM_SERVICE_ID);

create table HOTEL.PUBLIC.GUEST_SERVICE
(
    GUEST_SERVICE_ID        BIGINT auto_increment,
    GUEST_ID                BIGINT not null,
    ROOM_SERVICE_ID         BIGINT not null,
    ROOM_SERVICE_ORDER_DATE DATE   not null,
    constraint GUEST_SERVICE_PK
        primary key (GUEST_SERVICE_ID),
    constraint GUEST_SERVICE_GUEST_GUEST_ID_FK
        foreign key (GUEST_ID) references HOTEL.PUBLIC.GUEST
            on update cascade on delete cascade,
    constraint GUEST_SERVICE_ROOM_SERVICE_ROOM_SERVICE_ID_FK
        foreign key (ROOM_SERVICE_ID) references HOTEL.PUBLIC.ROOM_SERVICE
            on update cascade on delete cascade
);

create table HOTEL.PUBLIC.BOOKING
(
    BOOKING_ID     BIGINT auto_increment,
    GUEST_ID       BIGINT not null,
    ROOM_ID        BIGINT not null,
    CHECK_IN_DATE  DATE   not null,
    CHECK_OUT_DATE DATE   not null,
    constraint BOOKING_GUEST_GUEST_ID_FK
        foreign key (GUEST_ID) references HOTEL.PUBLIC.GUEST,
    constraint BOOKING_ROOM_ROOM_ID_FK
        foreign key (ROOM_ID) references HOTEL.PUBLIC.ROOM
);

create unique index HOTEL.PUBLIC.BOOKING_GUEST_ID_UINDEX
    on HOTEL.PUBLIC.BOOKING (GUEST_ID);

create unique index HOTEL.PUBLIC.BOOKING_ID_UINDEX
    on HOTEL.PUBLIC.BOOKING (BOOKING_ID);

alter table HOTEL.PUBLIC.BOOKING
    add constraint BOOKING_PK
        primary key (BOOKING_ID);