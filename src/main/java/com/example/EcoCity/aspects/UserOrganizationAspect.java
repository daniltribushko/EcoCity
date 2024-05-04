package com.example.EcoCity.aspects;

import com.example.EcoCity.exceptions.users.UserNotOrganizationException;
import com.example.EcoCity.models.entities.User;
import com.example.EcoCity.services.db.DBServiceUser;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Tribushko Danil
 * @since 01.05.2024
 * <p>
 * Аспект для проверки является ли пользователь организацией
 */
@Aspect
@Component
public class UserOrganizationAspect {
    private final DBServiceUser dbServiceUser;

    @Autowired
    public UserOrganizationAspect(DBServiceUser dbServiceUser) {
        this.dbServiceUser = dbServiceUser;
    }

    @Before(value = "@annotation(com.example.EcoCity.aspects.annotations.CheckUserOrganization) &&" +
            "args(email,..)", argNames = "email")
    public void checkUserIsOrganization(String email) {
        User user = dbServiceUser.findByEmail(email);
        if (!user.isOrganization()) {
            throw new UserNotOrganizationException(email);
        }
    }
}
