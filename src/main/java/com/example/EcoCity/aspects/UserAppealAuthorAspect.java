package com.example.EcoCity.aspects;

import com.example.EcoCity.exceptions.users.UserNotAppealAuthorException;
import com.example.EcoCity.models.entities.Appeal;
import com.example.EcoCity.models.entities.User;
import com.example.EcoCity.services.FileService;
import com.example.EcoCity.services.db.DBServiceAppeal;
import com.example.EcoCity.services.db.DBServiceUser;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author Tribushko Danil
 * @since 13.04.2024
 * <p>
 * Аспект для проверки является ли пользователь автором обращения
 */
@Aspect
@Component
public class UserAppealAuthorAspect {
    private final DBServiceUser dbServiceUser;
    private final DBServiceAppeal dbServiceAppeal;
    private final FileService fileService;

    @Value("${appeal.photo.path}")
    private String appealPhotoUrl;

    @Autowired
    public UserAppealAuthorAspect(DBServiceUser dbServiceUser,
                                  DBServiceAppeal dbServiceAppeal,
                                  FileService fileService) {
        this.dbServiceUser = dbServiceUser;
        this.dbServiceAppeal = dbServiceAppeal;
        this.fileService = fileService;
    }

    @Before(value = "@annotation(com.example.EcoCity.aspects.annotations.CheckUserAppealAuthor) && " +
            "!execution(public void deleteAllFiles(..)) && args(email, id,..)", argNames = "email, id")
    public void checkUserAppealAuthor(String email, Long id) {
        User user = dbServiceUser.findByEmail(email);
        Appeal appeal = dbServiceAppeal.findById(id);
        if (!user.isAdmin() && !Objects.equals(user.getId(), appeal.getAuthor().getId())) {
            throw new UserNotAppealAuthorException(email, id);
        }
    }

    @Before(value = "@annotation(com.example.EcoCity.aspects.annotations.CheckUserAppealAuthor) &&" +
            "execution(public void deleteAllFiles(..)) && args(email,id)", argNames = "email,id")
    public void checkUserAppealAuthorAndDeleteFiles(String email, Long id) {
        User user = dbServiceUser.findByEmail(email);
        Appeal appeal = dbServiceAppeal.findById(id);
        if (!user.isAdmin() && !Objects.equals(user.getId(), appeal.getAuthor().getId())) {
            throw new UserNotAppealAuthorException(email, id);
        } else {
            appeal.getPhotos().forEach(o -> fileService.delete(appealPhotoUrl, o.getPhotoUrl()));
        }
    }
}
