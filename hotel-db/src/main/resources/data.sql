INSERT INTO hotel.guest (first_name, last_name, email, password, role)
VALUES ('Admin', 'Admin', 'admin@mail.com', '$2a$12$l10zaZsfMHAW5StiZGDTSeApmG1mvNqnOMTCLvhfq.pw/hfe3OBfi',
        'ROLE_ADMIN');
INSERT INTO hotel.guest (first_name, last_name, email, password, role)
VALUES ('Igor', 'Ivanov', 'ivanov@mail.com', '$2a$12$BfnStYr2IiA8xBC2Wb5UhuApfbC1BsPPbjmcQph6RIChR4AAc4mZK',
        'ROLE_GUEST');
INSERT INTO hotel.guest (first_name, last_name, email, password, role)
VALUES ('Sergei', 'Petrov', 'petrov@mail.com', '$2a$12$BfnStYr2IiA8xBC2Wb5UhuApfbC1BsPPbjmcQph6RIChR4AAc4mZK',
        'ROLE_GUEST');
INSERT INTO hotel.guest (first_name, last_name, email, password, role)
VALUES ('Ivan', 'Sidorov', 'sidorov@mail.com', '$2a$12$BfnStYr2IiA8xBC2Wb5UhuApfbC1BsPPbjmcQph6RIChR4AAc4mZK',
        'ROLE_GUEST');
INSERT INTO hotel.guest (first_name, last_name, email, password, role)
VALUES ('Alex', 'Kirov', 'kirov@mail.com', '$2a$12$BfnStYr2IiA8xBC2Wb5UhuApfbC1BsPPbjmcQph6RIChR4AAc4mZK',
        'ROLE_GUEST');

INSERT INTO hotel.room (capacity, price, room_status, stars_rating)
VALUES (1, 45.3, 'VACANT', 3);
INSERT INTO hotel.room (capacity, price, room_status, stars_rating)
VALUES (1, 49.3, 'VACANT', 3);
INSERT INTO hotel.room (capacity, price, room_status, stars_rating)
VALUES (2, 78.3, 'OCCUPIED', 3);
INSERT INTO hotel.room (capacity, price, room_status, stars_rating)
VALUES (3, 89.3, 'VACANT', 3);

INSERT INTO hotel.room_service (service_type, price)
VALUES ('CLEANING', 23.6);
INSERT INTO hotel.room_service (service_type, price)
VALUES ('REPAIR', 45.3);
INSERT INTO hotel.room_service (service_type, price)
VALUES ('MAINTENANCE', 36.2);

INSERT INTO hotel.guest_service (guest_id, room_service_id, room_service_order_date)
VALUES (1, 1, '2023-09-10');
INSERT INTO hotel.guest_service (guest_id, room_service_id, room_service_order_date)
VALUES (1, 1, '2023-09-11');
INSERT INTO hotel.guest_service (guest_id, room_service_id, room_service_order_date)
VALUES (1, 1, '2023-09-12');
INSERT INTO hotel.guest_service (guest_id, room_service_id, room_service_order_date)
VALUES (2, 1, '2023-09-04');
INSERT INTO hotel.guest_service (guest_id, room_service_id, room_service_order_date)
VALUES (2, 1, '2023-09-05');
INSERT INTO hotel.guest_service (guest_id, room_service_id, room_service_order_date)
VALUES (2, 2, '2023-09-06');
INSERT INTO hotel.guest_service (guest_id, room_service_id, room_service_order_date)
VALUES (3, 1, '2023-09-08');

INSERT INTO hotel.booking (guest_id, room_id, check_in_date, check_out_date)
VALUES (1, 1, '2023-09-10', '2023-09-12');
INSERT INTO hotel.booking (guest_id, room_id, check_in_date, check_out_date)
VALUES (2, 2, '2023-09-04', '2023-09-06');
INSERT INTO hotel.booking (guest_id, room_id, check_in_date, check_out_date)
VALUES (3, 3, '2023-09-08', '2023-09-09');
