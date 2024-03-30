package com.example.EcoCity.exceptions.roles;

import com.example.EcoCity.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 29.03.2024
 */
public class RoleByNameNotFoundException extends GlobalAppException {
    public RoleByNameNotFoundException(String name) {
        super(404, "Role: " + name + " not found");
    }
}
