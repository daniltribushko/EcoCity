package com.example.EcoCity.services.iml;

import com.example.EcoCity.models.dto.request.UpdateUserRequest;
import com.example.EcoCity.models.dto.response.UserResponse;
import com.example.EcoCity.models.entities.User;
import com.example.EcoCity.services.db.DBServiceUser;
import com.example.EcoCity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tribushko Danil
 * @since 30.03.2024
 * <p>
 * Реализация сервиса по работе с пользователями
 */
@Service
public class UserServiceImp implements UserService {
    private final DBServiceUser dbServiceUser;

    @Autowired
    public UserServiceImp(DBServiceUser dbServiceUser){
        this.dbServiceUser = dbServiceUser;
    }

    @Override
    public UserResponse findById(Long id) {
        return UserResponse.mapFromEntity(dbServiceUser.findById(id));
    }

    @Override
    public UserResponse update(String email, UpdateUserRequest request) {
        User user = dbServiceUser.findById(request.getId());
        String userEmail = request.getEmail();
        String userSurname = request.getSurname();
        String userName = request.getName();
        if (userEmail != null){
            user.setEmail(userEmail);
        }
        if (userSurname != null){
            user.setSurname(userSurname);
        }
        if (userName != null){
            user.setName(userName);
        }
        dbServiceUser.save(user);
        return UserResponse.mapFromEntity(user);
    }

    @Override
    public void delete(String email, Long id) {
        dbServiceUser.delete(id);
    }
}
