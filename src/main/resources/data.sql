INSERT INTO users (FIRST_NAME, LAST_NAME, PASSWORD, ROLE, USERNAME) VALUES ('cold', '234', '$2a$10$uI1MYN81H/3Gf8yopA0RSedioJ8uwZOciCk5zC4toKNJP6KiWG3BS', 'ROLE_MENTOR', 'user1');

INSERT INTO users (FIRST_NAME, LAST_NAME, PASSWORD, ROLE, USERNAME) VALUES ('Kamran', 'Abbasi', '$2a$10$uI1MYN81H/3Gf8yopA0RSedioJ8uwZOciCk5zC4toKNJP6KiWG3BS', 'ROLE_REVIEWER', 'user2');

INSERT INTO mentees(ROLL_NUMBER, EMAIL, FIRST_NAME, SUR_NAME) VALUES ('R001' ,'fawad@gmail.com','fawad','khan');

INSERT INTO mentees(ROLL_NUMBER,EMAIL, FIRST_NAME, SUR_NAME) VALUES ('R002' ,'cold234@gmail.com','Cold','234');

INSERT INTO mentees(ROLL_NUMBER,EMAIL, FIRST_NAME, SUR_NAME) VALUES ('R003' , 'muneeza@gmail.com','Muneeza','Kamran');

INSERT INTO notes (DATE_OF_CREATION, TEXT, mentee_id) VALUES ( '2021-07-12', 'Note related to Fawad', 1 );

INSERT INTO notes (DATE_OF_CREATION, TEXT, mentee_id) VALUES ( '2021-07-12', 'Note related to cold234', 2 );
