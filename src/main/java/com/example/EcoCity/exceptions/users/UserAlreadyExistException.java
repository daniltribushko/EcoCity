package com.example.EcoCity.exceptions.users;

import com.example.EcoCity.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 29.03.2024
 */
public class UserAlreadyExistException extends GlobalAppException {
    public UserAlreadyExistException(String email) {
        super(409, "User: " + email + " already exist");
    }
}
