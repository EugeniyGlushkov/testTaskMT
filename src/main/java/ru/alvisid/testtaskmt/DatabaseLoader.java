package ru.alvisid.testtaskmt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.alvisid.testtaskmt.model.User;

import java.time.LocalDate;
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
        this.repository.save(new User(1, "Paul", "paul@yandex.ru", "paul", LocalDate.of(1995, 10, 22)));
        this.repository.save(new User(2, "Jhon", "jhon@mail.ru", "jhon", LocalDate.of(2001, 5, 12)));
        this.repository.save(new User(3, "Ann", "ann@list.ru", "ann", LocalDate.of(2003, 3, 1)));
        this.repository.save(new User(4, "Mary", "mary@gmail.com", "mary", LocalDate.of(2000, 2, 9)));
    }
}
