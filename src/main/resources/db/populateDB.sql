DELETE FROM users;

ALTER SEQUENCE users_id_seq RESTART WITH 1;

/*passwords:
paul
jhon
ann
mary*/

INSERT INTO users(name, email, password, birth_date) VALUES
('Paul', 'paul@yandex.ru', '$2a$10$B41BWLJ2mvi2UI5cWfoamOlfMHTFcQ0xhMoj3ZsHRd2v0B2DEW2dy', '1995-10-22'),
('Jhon', 'jhon@mail.ru', '$2a$10$dWhsSLTPGNoqbCFb4Tlm.ebvOBQmy.J3rC6BJyiOWt5QxY.Uxmszi', '2001-05-12'),
('Ann', 'ann@list.ru', '$2a$10$.DjPKs59.Jvf/paHPE6.eO7rOGPtMGlLoVzPqZbMF0WH2TEpxBdMK', '2003-03-01'),
('Mary', 'mary@gmail.com', '$2a$10$Qt3Wbm57kpQcdRwsDoRG9OsPP7wADkEyapKwM/APjrXEL5VburKy.', '2000-02-09');