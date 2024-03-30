package com.example.EcoCity.exceptions.users;

import com.example.EcoCity.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 29.03.2024
 */
public class UserByEmailNotFoundException extends GlobalAppException {
    public UserByEmailNotFoundException(String email) {
        super(404, "User: " + email + " not found");
    }
}
