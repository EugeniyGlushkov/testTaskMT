package ru.alvisid.testtaskmt.repository;

import org.springframework.data.repository.CrudRepository;
import ru.alvisid.testtaskmt.model.User;

public interface UserRepository extends CrudRepository <User, Long> {}
