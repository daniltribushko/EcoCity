package com.example.EcoCity.services.iml;

import com.example.EcoCity.aspects.annotations.CheckUserAdmin;
import com.example.EcoCity.exceptions.users.WrongRecordStateException;
import com.example.EcoCity.models.dto.request.UpdateUserRequest;
import com.example.EcoCity.models.dto.response.UserResponse;
import com.example.EcoCity.models.dto.response.UsersResponse;
import com.example.EcoCity.models.entities.User;
import com.example.EcoCity.models.enums.RecordState;
import com.example.EcoCity.services.db.DBServiceUser;
import com.example.EcoCity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
    public UserServiceImp(DBServiceUser dbServiceUser) {
        this.dbServiceUser = dbServiceUser;
    }

    @Override
    public UserResponse findById(Long id) {
        return UserResponse.mapFromEntity(dbServiceUser.findById(id));
    }

    @Override
    @CheckUserAdmin
    public UserResponse update(String email, UpdateUserRequest request) {
        User user = dbServiceUser.findById(request.getId());
        String userEmail = request.getEmail();
        String userSurname = request.getSurname();
        String userName = request.getName();
        if (userEmail != null) {
            user.setEmail(userEmail);
        }
        if (userSurname != null) {
            user.setSurname(userSurname);
        }
        if (userName != null) {
            user.setName(userName);
        }
        dbServiceUser.save(user);
        return UserResponse.mapFromEntity(user);
    }

    @Override
    @CheckUserAdmin
    public void delete(String email, Long id) {
        dbServiceUser.delete(id);
    }

    @Override
    @CheckUserAdmin
    public UsersResponse findAll(String email) {
        return new UsersResponse(dbServiceUser.findAll()
                .stream()
                .map(UserResponse::mapFromEntity)
                .toList());
    }

    @Override
    public UsersResponse findAllWithPagination(Integer page,
                                               Integer perPage,
                                               String recordState,
                                               LocalDateTime createDate,
                                               LocalDateTime lastDateOnOnline,
                                               String role) {
        RecordState state;
        switch (recordState){
            case "ACTIVE" -> state = RecordState.ACTIVE;
            case "DELETED" -> state = RecordState.DELETED;
            default -> throw new WrongRecordStateException();
        }
        return new UsersResponse(dbServiceUser.findAllWithPagination(page,
                        perPage,
                        state,
                        createDate,
                        lastDateOnOnline,
                        role)
                .stream()
                .map(UserResponse::mapFromEntity)
                .toList());
    }
}
