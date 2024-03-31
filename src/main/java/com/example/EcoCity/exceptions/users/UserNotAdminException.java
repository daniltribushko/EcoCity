package com.example.EcoCity.exceptions.users;

import com.example.EcoCity.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 31.03.2024
 */
public class UserNotAdminException extends GlobalAppException {
    public UserNotAdminException(String email) {
        super(403, "User: " + email + " not admin");
    }
}
