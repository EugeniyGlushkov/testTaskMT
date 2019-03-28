package ru.alvisid.testtaskmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//@EnableCaching
@EnableTransactionManagement
@EntityScan("ru.alvisid.testtaskmt.model")
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
