package com.example.EcoCity.exceptions.users;

import com.example.EcoCity.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 29.03.2024
 */
public class UserByIdNotFoundException extends GlobalAppException {
    public UserByIdNotFoundException(Long id) {
        super(404, "User with " + id + " id not found");
    }
}
