package ru.alvisid.testtaskmt;

import org.springframework.data.repository.CrudRepository;
import ru.alvisid.testtaskmt.model.User;

public interface UserRepository extends CrudRepository <User, Integer> {}
