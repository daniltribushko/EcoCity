package com.example.EcoCity.services.db.imp;

import com.example.EcoCity.exceptions.users.UserAlreadyExistException;
import com.example.EcoCity.exceptions.users.UserByEmailNotFoundException;
import com.example.EcoCity.exceptions.users.UserByIdNotFoundException;
import com.example.EcoCity.models.entities.Role;
import com.example.EcoCity.models.entities.User;
import com.example.EcoCity.models.enums.RecordState;
import com.example.EcoCity.repositories.UserPaginationRepository;
import com.example.EcoCity.services.db.DBServiceUser;
import com.example.EcoCity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @author Tribushko Danil
 * @since 29.03.2024
 * <p>
 * Реализация сервиса по работе с пользователями в бд
 */
@Service
public class DBServiceUserImp implements DBServiceUser {
    private final UserRepository userRepository;
    private final UserPaginationRepository userPaginationRepository;

    @Autowired
    public DBServiceUserImp(UserRepository userRepository,
                            UserPaginationRepository userPaginationRepository){
        this.userRepository = userRepository;
        this.userPaginationRepository = userPaginationRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserByIdNotFoundException(id));
    }

    @Override
    public void save(User object) {
        String email = object.getUsername();
        if (userRepository.existsByEmail(email)){
            throw new UserAlreadyExistException(email);
        }
        userRepository.save(object);
    }

    @Override
    public void delete(Long id) {
        User user = findById(id);
        userRepository.delete(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return findByEmail(email);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserByEmailNotFoundException(email));
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAllWithPagination(Integer page,
                                            Integer perPage,
                                            RecordState recordState,
                                            LocalDateTime createDate,
                                            LocalDateTime lastDateOnOnline,
                                            String role) {
        return userPaginationRepository.findAll(PageRequest.of(page, page))
                .stream()
                .filter(u -> (recordState == null || Objects.equals(u.getRecordState(), recordState)) &&
                        (createDate == null || Objects.equals(u.getCreateDate(), createDate)) &&
                        (lastDateOnOnline == null || Objects.equals(u.getLastOnlineDate(), lastDateOnOnline)) &&
                        (role == null || isUserHasRole(u, role)))
                .toList();
    }

    private boolean isUserHasRole(User user, String roleName){
        boolean result = false;
        for (Role role : user.getRoles()){
            if (Objects.equals(role.getName(), roleName)){
                result = true;
                break;
            }
        }
        return result;
    }
}
