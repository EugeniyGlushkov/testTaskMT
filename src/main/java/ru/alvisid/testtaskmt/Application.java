package ru.alvisid.testtaskmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        /*System.out.println("!!!");
        System.out.println("1<<" + new BCryptPasswordEncoder().encode("paul") + ">>");
        System.out.println("2<<" + new BCryptPasswordEncoder().encode("jhon") + ">>");
        System.out.println("3<<" + new BCryptPasswordEncoder().encode("ann") + ">>");
        System.out.println("4<<" + new BCryptPasswordEncoder().encode("mary") + ">>");
        System.out.println("!!!")*/;
        SpringApplication.run(Application.class, args);
    }
}
