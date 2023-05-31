INSERT INTO _user (email, username, password, is_admin, is_confirmed)
VALUES ('sara@yahoo.com', 'Sara', 'Password1@', true, false),
       ('lara@yahoo.com', 'Lara', '$2a$10$bdR.wy1ImvmHhCC7DNzfVODBiqUGI09Pf1ywPq5uif9FXbCJfDxIu', false, true),
       ('cara@yahoo.com', 'Cara', 'Password3@', false, true),
       ('vara@yahoo.com', 'Vara', 'Password4@', true, true),
       ('para@yahoo.com', 'Para', 'Password5@', false, false),
       ('iara@yahoo.com', 'Iara', 'Password6@', false, true),
       ('Tara@yahoo.com', 'Tara', 'Password7@', true, false),
       ('Oara@yahoo.com', 'Oara', 'Password8@', false, true);


INSERT INTO _event
(   PHOTO, TITLE, DESCRIPTION, START_DATE_TIME, END_DATE_TIME, ADDRESS, EVENT_LINK, TICKET_LINK, IS_FREE,
    IS_PET_FRIENDLY , IS_KID_FRIENDLY , ADMIN_EMAIL , TAG_NAME, EVENT_STATUS
)
VALUES ('event1.jpg', 'Event 1', 'Description for Event 1', '2023-05-25T10:00:00', '2023-05-25T12:00:00', '123 Main St, City',
        'https://www.example.com/event1', 'https://www.example.com/tickets/event1', true, true, false, 'sara@example.com',
        'CONCERT', 0),
       ('event2.jpg', 'Event 2', 'Description for Event 2', '2023-05-26T14:00:00', '2023-05-26T17:00:00',
        '456 Elm St, City', 'https://www.example.com/event2', 'https://www.example.com/tickets/event2', false, true, true,
        'lara@example.com', 'FOOD', 1),
       ('event3.jpg', 'Event 3', 'Description for Event 3', '2023-05-27T18:00:00', '2023-05-27T22:00:00', '789 Oak St, City',
        'https://www.example.com/event3', 'https://www.example.com/tickets/event3', true, false, true, 'cara@example.com',
        'FESTIVAL', 0),
       ('event4.jpg', 'Event 4', 'Description for Event 4', '2023-05-28T09:00:00', '2023-05-28T12:00:00', '321 Maple St, City',
        'https://www.example.com/event4', 'https://www.example.com/tickets/event4', true, true, true, 'para@example.com',
        'SPECTACOL', 2),
       ('event5.jpg', 'Event 5', 'Description for Event 5', '2023-05-29T16:00:00', '2023-05-29T18:00:00', '654 Pine St, City',
        'https://www.example.com/event5', 'https://www.example.com/tickets/event5', false, false, true, 'iara@example.com',
        'TEATRU', 0),
       ('event6.jpg', 'Event 6', 'Description for Event 6', '2023-05-30T20:00:00', '2023-05-30T22:30:00', '987 Cedar St, City',
        'https://www.example.com/event6', 'https://www.example.com/tickets/event6', true, true, true, 'tara@example.com',
        'EXPOZITIE', 1);