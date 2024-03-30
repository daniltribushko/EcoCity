package com.example.EcoCity.utils;

import com.example.EcoCity.models.entities.Role;
import com.example.EcoCity.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Tribushko Danil
 * @since 30.03.2024
 * <p>
 * Класс для инициализации ролей в бд при запуске приложения
 */
@Component
public class RolesCommandLineRunner implements CommandLineRunner {
    private final RoleRepository roleRepository;

    @Autowired
    public RolesCommandLineRunner(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.findAll().isEmpty()) {
            roleRepository.save(new Role("USER"));
            roleRepository.save(new Role("ORGANIZATION"));
            roleRepository.save(new Role("ADMIN"));
        }
    }
}
