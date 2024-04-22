package com.example.EcoCity.schedulers;

import com.example.EcoCity.models.entities.User;
import com.example.EcoCity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Tribusko Danil
 * @since 21.04.2024
 * <p>
 * Щедулер для блокировки неактивных пользователей
 */
@Component
@PropertySource("classpath:application.yaml")
public class UserBlockScheduler {
    private final UserRepository userRepository;

    @Autowired
    public UserBlockScheduler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Scheduled(cron = "0 0 0 1 JAN,JUN *", zone = "Asia/Yekaterinburg")
    public void deleteNotActiveUser() {
        List<User> users = userRepository.findAllByLastOnlineDateBefore(LocalDateTime.now().minusMonths(6));
        userRepository.deleteAll(users);
    }
}
