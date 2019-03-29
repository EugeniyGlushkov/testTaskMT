package ru.alvisid.testtaskmt.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.alvisid.testtaskmt.TO.UserTo;
import ru.alvisid.testtaskmt.model.User;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserUtul {
    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private UserUtul() {
    }

    public static User toUser(UserTo userTo) {
        String password = userTo.getPas();

        if (StringUtils.isNotBlank(password) && password.length() >= 3 && password.length() <= 100) {
            password = encoder.encode(password);
        } else if (StringUtils.isEmpty(password) && !Objects.isNull(userTo.getId())) {
            password = null;
        } else {
            throw new RuntimeException("Password must has not only whitespace and be in interval from 3 to 100 symbols.");
        }

        return new User(userTo.getId()
                , userTo.getName()
                , userTo.getEmail()
                , password
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
