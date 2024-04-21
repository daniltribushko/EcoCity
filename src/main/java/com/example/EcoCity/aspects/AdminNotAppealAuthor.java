package com.example.EcoCity.aspects;

import com.example.EcoCity.exceptions.users.AdminChangeHisAppealStatusException;
import com.example.EcoCity.exceptions.users.UserNotAdminException;
import com.example.EcoCity.models.entities.Appeal;
import com.example.EcoCity.models.entities.User;
import com.example.EcoCity.services.db.DBServiceAppeal;
import com.example.EcoCity.services.db.DBServiceUser;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author Tribushko Danil
 * @since 21.04.2024
 * <p>
 * Аспект для проверки, является ли администратор автором обращения
 */
@Aspect
@Component
public class AdminNotAppealAuthor {
    private final DBServiceUser dbServiceUser;
    private final DBServiceAppeal dbServiceAppeal;

    @Autowired
    public AdminNotAppealAuthor(DBServiceUser dbServiceUser, DBServiceAppeal dbServiceAppeal) {
        this.dbServiceUser = dbServiceUser;
        this.dbServiceAppeal = dbServiceAppeal;
    }

    @Before(value = "@annotation(com.example.EcoCity.aspects.annotations.CheckAdminNotAppealAuthor) && " +
            "args(email,id)",argNames = "email,id")
    public void checkAdminNotAppealAuthor(String email, Long id){
        User user = dbServiceUser.findByEmail(email);
        Appeal appeal = dbServiceAppeal.findById(id);
        if (!user.isAdmin()){
            throw new UserNotAdminException(email);
        } else if (Objects.equals(user.getId(), appeal.getAuthor().getId())){
            throw new AdminChangeHisAppealStatusException(email, id);
        }
    }
}
