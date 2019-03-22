package ru.alvisid.testtaskmt.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alvisid.testtaskmt.model.User;
import org.springframework.http.ResponseEntity;
import ru.alvisid.testtaskmt.repository.UserRepository;
import ru.alvisid.testtaskmt.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HomeController {
    private UserService service;

    @Autowired
    public HomeController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public ResponseEntity <List <User>> users() throws Exception {
        return new ResponseEntity <List <User>>(service.getAll(), HttpStatus.OK);
    }
}
