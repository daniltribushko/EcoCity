package com.example.EcoCity.aspects;

import com.example.EcoCity.exceptions.users.UserNotEventCreatorException;
import com.example.EcoCity.models.entities.Event;
import com.example.EcoCity.models.entities.User;
import com.example.EcoCity.services.db.DBServiceEvent;
import com.example.EcoCity.services.db.DBServiceUser;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author Tribushko Danil
 * @since 02.05.2024
 * <p>
 * Аспект для проверки является ли пользователь автором мероприятия
 */
@Aspect
@Component
public class UserEventAuthorAspect {
    private DBServiceUser dbServiceUser;
    private DBServiceEvent dbServiceEvent;

    @Autowired
    public UserEventAuthorAspect(DBServiceUser dbServiceUser,
                                 DBServiceEvent dbServiceEvent) {
        this.dbServiceUser = dbServiceUser;
        this.dbServiceEvent = dbServiceEvent;
    }

    @Before(value = "@annotation(com.example.EcoCity.aspects.annotations.CheckUserEventAuthor) &&" +
            "args(email,id,..)", argNames = "email,id")
    public void checkUserEventAuthor(String email, Long id) {
        User user = dbServiceUser.findByEmail(email);
        Event event = dbServiceEvent.findById(id);
        if (!Objects.equals(user.getId(), event.getCreator().getId()) && !user.isAdmin()) {
            throw new UserNotEventCreatorException(email, id);
        }
    }
}
