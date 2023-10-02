create table PRODUCT
(
    MAKER CHARACTER VARYING(10),
    MODEL CHARACTER VARYING(50) not null,
    TYPE  CHARACTER VARYING(50),
    constraint PRODUCT_PK
        primary key (MODEL)
);

create table LAPTOP
(
    CODE   INTEGER not null,
    MODEL  CHARACTER VARYING(50),
    SPEED  SMALLINT,
    RAM    SMALLINT,
    HD     REAL,
    PRICE  DOUBLE PRECISION,
    SCREEN TINYINT,
    constraint LAPTOP_PRODUCT_MODEL_FK
        foreign key (MODEL) references PRODUCT
            on update cascade on delete cascade
);

create unique index LAPTOP_CODE_UINDEX
    on LAPTOP (CODE);

alter table LAPTOP
    add constraint LAPTOP_PK
        primary key (CODE);

create table PC
(
    CODE  INTEGER not null,
    MODEL CHARACTER VARYING(50),
    SPEED SMALLINT,
    RAM   SMALLINT,
    HD    REAL,
    CD    CHARACTER VARYING(10),
    PRICE DOUBLE PRECISION,
    constraint PC_PRODUCT_MODEL_FK
        foreign key (MODEL) references PRODUCT
);

create unique index PC_CODE_UINDEX
    on PC (CODE);

alter table PC
    add constraint PC_PK
        primary key (CODE);

create table PRINTER
(
    CODE  INTEGER not null,
    MODEL CHARACTER VARYING(50),
    COLOR CHARACTER,
    TYPE  CHARACTER VARYING(10),
    PRICE DOUBLE PRECISION,
    constraint PRINTER_PK
        primary key (CODE),
    constraint PRINTER_PRODUCT_MODEL_FK
        foreign key (MODEL) references PRODUCT
);

create unique index PRINTER_CODE_UINDEX
    on PRINTER (CODE);