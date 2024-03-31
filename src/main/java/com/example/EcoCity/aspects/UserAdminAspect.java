package com.example.EcoCity.aspects;

import com.example.EcoCity.exceptions.users.UserNotAdminException;
import com.example.EcoCity.models.entities.User;
import com.example.EcoCity.services.users.DBServiceUser;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Tribushko Danil
 * @since 31.03.2024
 * <p>
 * Аспект для проверки является ли пользователь администратором
 */
@Aspect
@Component
public class UserAdminAspect {
    private final DBServiceUser dbServiceUser;

    @Autowired
    public UserAdminAspect(DBServiceUser dbServiceUser) {
        this.dbServiceUser = dbServiceUser;
    }

    @Before(value = "@annotation(com.example.EcoCity.aspects.annotations.CheckUserAdmin) && " +
            "args(email,..)", argNames = "email")
    public void checkUserIsAdmin(String email) {
        User user = dbServiceUser.findByEmail(email);
        if (!user.isAdmin()) {
            throw new UserNotAdminException(email);
        }
    }
}
