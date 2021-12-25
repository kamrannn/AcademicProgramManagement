DROP TABLE IF EXISTS T_USERS;

CREATE TABLE users
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(250) NOT NULL,
    last_name  VARCHAR(250) NOT NULL,
    password   VARCHAR(250) NOT NULL,
    role       VARCHAR(250) NOT NULL,
    username   VARCHAR(250) NOT  NULL,
    UNIQUE (username)
);

DROP TABLE IF EXISTS mentees;

CREATE TABLE mentees
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    roll_number VARCHAR(250) NOT NULL,
    email      VARCHAR(250) NOT NULL,
    first_name VARCHAR(250),
    sur_name   VARCHAR(250),
    UNIQUE (email)
);

DROP TABLE IF EXISTS notes;

CREATE TABLE notes
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    date_of_creation DATE,
    text       VARCHAR(250) NOT NULL,
    mentee_id int,
    FOREIGN KEY (mentee_id) REFERENCES mentees (id)
);