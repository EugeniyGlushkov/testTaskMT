package ru.alvisid.testtaskmt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.GregorianCalendar;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final UserRepository repository;

    @Autowired
    public DatabaseLoader(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.repository.save(new User("Paul", "paul@yandex.ru", new GregorianCalendar(2001, 5, 12)));
        this.repository.save(new User("Jhon", "jhon@mail.ru", new GregorianCalendar(2002, 10, 25)));
        this.repository.save(new User("Ann", "ann@list.ru", new GregorianCalendar(2003, 3, 1)));
        this.repository.save(new User("Mary", "mary@gmail.com", new GregorianCalendar(2000, 2, 9)));
    }
}
