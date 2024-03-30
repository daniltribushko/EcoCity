package com.example.EcoCity.services.iml.users;

import com.example.EcoCity.exceptions.users.UserByEmailNotFoundException;
import com.example.EcoCity.exceptions.users.UserByIdNotFoundException;
import com.example.EcoCity.models.entities.User;
import com.example.EcoCity.services.users.DBServiceUser;
import com.example.EcoCity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Tribushko Danil
 * @since 29.03.2024
 * <p>
 * Реализация сервиса по работе с пользователями в бд
 */
@Service
public class DBServiceUserImp implements DBServiceUser {
    private final UserRepository userRepository;

    @Autowired
    public DBServiceUserImp(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserByIdNotFoundException(id));
    }

    @Override
    public void save(User object) {
        userRepository.save(object);
    }

    @Override
    public void delete(User object) {
        userRepository.delete(object);
    }

    @Override
    public void update(User object) {
        save(object);
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
    public boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
