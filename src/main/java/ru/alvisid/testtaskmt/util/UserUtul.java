package ru.alvisid.testtaskmt.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.alvisid.testtaskmt.TO.UserTo;
import ru.alvisid.testtaskmt.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserUtul {
    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private UserUtul() {
    }

    public static User toUser(UserTo userTo) {

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(userTo);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        String password = userTo.getPassword();

        if (StringUtils.isBlank(password) || password.length() < 3 || password.length() > 100) {
            throw new IllegalArgumentException("Password is wrong, password must be not blank, lower 100 and more 3 char.");
        }

        return new User(userTo.getId()
                , userTo.getName()
                , userTo.getEmail()
                , encoder.encode(userTo.getPassword())
                , userTo.getBirthDate());
    }

    public static UserTo toUserTo(User user) {
        return new UserTo(user.getId()
                , user.getName()
                , user.getEmail()
                , ""
                , user.getBirthDate());
    }

    public static List <User> toUserList(List <? extends UserTo> usersToList) {
        return usersToList.stream().map(userTo -> toUser(userTo)).collect(Collectors.toList());
    }

    public static List <UserTo> toUserToList(List <? extends User> usersList) {
        return usersList.stream().map(user -> toUserTo(user)).collect(Collectors.toList());
    }
}
