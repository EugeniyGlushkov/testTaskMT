package ru.alvisid.testtaskmt.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import ru.alvisid.testtaskmt.model.User;
import org.springframework.http.ResponseEntity;
import ru.alvisid.testtaskmt.repository.UserRepository;
import ru.alvisid.testtaskmt.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public ResponseEntity <List <User>> users() throws Exception {
        return new ResponseEntity <List <User>>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/user/get/{id}")
    @ResponseBody
    public ResponseEntity<User> getUser(@PathVariable Integer id) throws Exception {
        return new ResponseEntity<User>(service.get(id), HttpStatus.OK);
    }

    @PostMapping("/user/add")
    public ResponseEntity<Void> addUser(@RequestBody User user) throws Exception {
        service.create(user);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/user/update")
    public ResponseEntity<Void> updateUser(@RequestBody User user) throws Exception {
        service.update(user);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/user/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return new ResponseEntity<User>(HttpStatus.OK);
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }
}
