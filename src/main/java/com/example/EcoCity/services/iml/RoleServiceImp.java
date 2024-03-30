package com.example.EcoCity.services.iml;

import com.example.EcoCity.exceptions.roles.RoleByNameNotFoundException;
import com.example.EcoCity.models.entities.Role;
import com.example.EcoCity.repositories.RoleRepository;
import com.example.EcoCity.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tribushko Danil
 * @since 29.03.2024
 * <p>
 * Реализация сервиса по работе с ролями
 */
@Service
public class RoleServiceImp implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImp(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new RoleByNameNotFoundException(name));
    }
}
