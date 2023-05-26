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
(   PHOTO, TITLE, DESCRIPTION, START_DATE_TIME, END_DATE_TIME, START_TIME, DURATION, ADDRESS,
    EVENT_LINK, TICKET_LINK, IS_FREE, IS_PET_FRIENDLY , IS_KID_FRIENDLY , ADMIN_EMAIL , TAG_NAME, STATUS
)
VALUES ('event1.jpg', 'Event 1', 'Description for Event 1', '2023-05-25T10:00:00', '2023-05-25T12:00:00',
        '10:00:00', '2', '123 Main St, City', 'https://www.example.com/event1',
        'https://www.example.com/tickets/event1', true, true, false, 'sara@example.com',
        'CONCERT', 0),
       ('event2.jpg', 'Event 2', 'Description for Event 2', '2023-05-26T14:00:00', '2023-05-26T16:00:00',
        '14:00:00', '3', '456 Elm St, City', 'https://www.example.com/event2',
        'https://www.example.com/tickets/event2', false, true, true, 'lara@example.com', 'FOOD', 1),
       ('event3.jpg', 'Event 3', 'Description for Event 3', '2023-05-27T18:00:00', '2023-05-27T20:00:00',
        '18:00:00', '4', '789 Oak St, City', 'https://www.example.com/event3', 'https://www.example.com/tickets/event3',
        true, false, true, 'cara@example.com', 'FESTIVAL', 0);