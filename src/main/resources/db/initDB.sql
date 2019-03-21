DROP TABLE IF EXISTS users;

/*
Users
contains:
-primary key;
-name;
-email;
-date of birth;
-password.
 */
CREATE TABLE users
(
  id          SERIAL PRIMARY KEY,
  name        VARCHAR NOT NULL,
  email       VARCHAR NOT NULL,
  password    VARCHAR NOT NULL,
  birth_date  DATE    NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx
  ON users (email);


