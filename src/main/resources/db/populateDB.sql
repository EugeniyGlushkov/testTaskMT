DELETE FROM users;

ALTER SEQUENCE users_id_seq RESTART WITH 1;

INSERT INTO users(name, email, password, birth_date) VALUES
('Paul', 'paul@yandex.ru', 'paul', '1995-10-22'),
('Jhon', 'jhon@mail.ru', 'jhon', '2001-05-12'),
('Ann', 'ann@list.ru', 'ann', '2003-03-01'),
('Mary', 'mary@gmail.com', 'mary', '2000-02-09');